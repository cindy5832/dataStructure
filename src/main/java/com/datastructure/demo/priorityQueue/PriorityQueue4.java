package com.datastructure.demo.priorityQueue;

import com.datastructure.demo.queue.Queue;

public class PriorityQueue4<E extends Priority> implements Queue<E> {
    Priority[] array;
    int size;

    /*
    1. 入堆新元素，加入道數組末尾 (索引位置 child)
    2. 不斷比較新加元素與它父節點 (parent)優先級
        - 若父節點優先級低，則向下移動，並找到下一個parent
        - 直到赴節點優先級更高或child = 0為止
     */

    public PriorityQueue4(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) return false;
        int child = size++;
        int parent = (child - 1) / 2;
        while (value.priority() > array[parent].priority() && child > 0) {
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = value;
        return true;
    }

    /*
    1. 交換堆頂和尾部元素，將父元素和兩個還自較大者交換
    2. 下潛
        - 從堆頂開始，將父元素與兩個孩子較大者交換
        - 直到父元素大於兩個孩子或沒有孩子為止
     */
    @Override
    public E poll() {
        if (isEmpty()) return null;
        swap(0, size - 1);
        size--;
        Priority e = array[size];
        array[size] = null; // help GC
        // 下潛
        down(0);
        return (E) e;
    }

    private void down(int parent) {
        int left = 2 * parent + 1;
        int right = left + 1;
        int max = parent; // 假設父元素優先級最高
        if (left < size && array[left].priority() > array[max].priority()) max = left;
        if (right < size && array[right].priority() > array[max].priority()) max = right;
        if(max != parent){ // 有孩子比父親大
            swap(max, parent);
            down(max);
        }

    }

    private void swap(int i, int j) {
        Priority t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    @Override
    public E peek() {
        if(isEmpty()) return null;
        return (E) array[0];
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
