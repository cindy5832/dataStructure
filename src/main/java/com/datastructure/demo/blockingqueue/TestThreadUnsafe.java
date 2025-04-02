package com.datastructure.demo.blockingqueue;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestThreadUnsafe {
    private final String[] array = new String[10];
    private int tail = 0;
    private int size = 0;
    ReentrantLock lock = new ReentrantLock();

    public void offer(String e) throws InterruptedException {
//        lock.lock(); // 加鎖
        lock.lockInterruptibly(); // 加鎖 (可以在隨意狀態打斷)
        Condition tailWaits = lock.newCondition();// 條件變量對象 集合
        try {
            while (isFull()) {
                // 滿了該做的事情，offer線程阻塞
                tailWaits.await(); // 當前線程加入 tailWaits，並且讓線程阻塞 tailWaits.signal()喚醒
            }
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            size++;
        } finally {
            lock.unlock(); // 解鎖
        }
    }

    public String toString() {
        return Arrays.toString(array);
    }

    private boolean isFull() {
        return size == array.length;
    }

    public static void main(String[] args) {
        TestThreadUnsafe queue = new TestThreadUnsafe();
        new Thread(() -> {
            try {
                queue.offer("e1");
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }, "t1").start();
        new Thread(() -> {
            try {
                queue.offer("e2");
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }, "t1").start();
    }
}
