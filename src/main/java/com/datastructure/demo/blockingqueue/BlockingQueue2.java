package com.datastructure.demo.blockingqueue;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue2<E> implements BlockingQueue<E> {
    private E[] array;
    private int head;
    private int tail;
    private AtomicInteger size = new AtomicInteger();

    public BlockingQueue2(int capacity) {
        array = (E[]) new Object[capacity];
    }

    private ReentrantLock tailLock = new ReentrantLock();
    private Condition tailWaits = tailLock.newCondition();

    private ReentrantLock headLock = new ReentrantLock();
    private Condition headWaits = headLock.newCondition();

    private boolean isEmpty() {
        return size.get() == 0;
    }

    private boolean isFull() {
        return size.get() == array.length;
    }

    @Override
    public void offer(E e) throws InterruptedException {
        int c; // 添加前元素個數
        tailLock.lockInterruptibly();
        try {
            // 1. 對列滿的時候等待
            while (isFull()) {
                tailWaits.await();
            }
            // 2. 不滿則入隊
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            // 3. 修改size
            c = size.getAndIncrement(); // size++
            if(c + 1 < array.length){
                tailWaits.signal();
            }
            headWaits.signal();
        } finally {
            tailLock.unlock();
        }
    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        return false;
    }

    @Override
    public E poll() throws InterruptedException {
        E e;
        int c; // 取走前的元素個數
        headLock.lockInterruptibly();
        try {
            // 1. 列隊空則等待
            while (isEmpty()) {
                headWaits.await();
            }
            // 2. 非空則出隊
            e = array[head];
            array[head] = null; // help GC
            if (++head == array.length) {
                head = 0;
            }
            // 3. 修改size
            c = size.getAndDecrement(); // size--
            if (c > 1) {
                headWaits.signal();
            }
            /*
                1. 獲取成員變量size的值 5
                2. 自減 4
                3. 結果寫回成員變量size 4
             */
            if (c == array.length) {
                try {
                    tailWaits.signal();
                } finally {
                    tailLock.unlock();
                }
            }
            return e;
        } finally {
            headLock.unlock();
        }
    }
}
