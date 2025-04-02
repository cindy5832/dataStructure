package com.datastructure.demo.queue;

import java.util.Iterator;

/* 僅用head, tail判斷空滿，head, tail需要換算成索引值
   Type Parameters: <E> - 隊列中元素類型
 */
public class ArrayQueue3<E> implements Queue<E>, Iterable<E> {
    /* 求模運算
       - 若除數是2的n次方
       - 那被除數的後n位即為餘數
       - 求被除數的後n位方法：與2^n-1按位與
     */
    private final E[] array;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue3(int capacity) {
        // 1. 拋異常
        // 2. 改成2^斯13->16 22 ->32
        if ((capacity & capacity - 1) != 0) {
            throw new IllegalArgumentException("capacity須為2的n次方");
        }
        array = (E[]) new Object[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) return false;
//        array[tail % array.length] = value;
        array[tail & (array.length - 1)] = value;
        tail++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) return null;
//        E value = array[head % array.length];
        E value = array[head & (array.length - 1)];
        head++;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
//        return array[head % array.length];
        return array[head & (array.length - 1)];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return tail - head == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
//                E value = array[p % array.length];
                E value = array[p & (array.length - 1)];
                p++;
                return value;
            }
        };
    }

    public static void main(String[] args) {
        // 求離c最近，比c大的2^n Method1
        int c = 30;
        int n = (int) (Math.log10(c - 1) / Math.log10(2)) + 1;
        System.out.println(n);
        System.out.println(1 << n);

        // 求離c最近，比c大的2^n Method2
        c -= 1;
        c |= c >> 2;
        c |= c >> 4;
        c |= c >> 8;
        c |= c >> 16;
        c += 1;
        System.out.println(c);
    }
}
