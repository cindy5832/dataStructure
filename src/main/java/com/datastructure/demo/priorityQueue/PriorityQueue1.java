package com.datastructure.demo.priorityQueue;

import com.datastructure.demo.queue.Queue;

// 無序數組
public class PriorityQueue1<E extends Priority> implements Queue<E> {
    Priority[] array;
    int size;

    public PriorityQueue1(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E e) {
        if (isFull()) return false;
        array[size++] = e;
        return true;
    }

    // 返回優先級最高的索引值
    private int selectMax() {
        int max = 0;
        for (int i = 1; i < size; i++) {
            if (array[i].priority() > array[max].priority()) {
                max = i;
            }
        }
        return max;
    }

    @Override
    public E poll() {
        if (isEmpty()) return null;
        int max = selectMax();
        E e = (E) array[max];
        remove(max);
        return e;
    }

    private void remove(int index) {
        if (index < size - 1) {
            System.arraycopy(array, index + 1, array, index, size - 1 - index);
        }
        size--;
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        int max = selectMax();
        return (E) array[max];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }
}
