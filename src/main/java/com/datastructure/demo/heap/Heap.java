package com.datastructure.demo.heap;

// 可擴容的heap, max用於指定是大頂堆還是小頂堆
public class Heap {
    int[] array;
    int size;
    boolean max;

    public int getSize() {
        return size;
    }

    public Heap(int capacity, boolean max) {
        this.array = new int[capacity];
        this.max = max;
        heapify();
    }

    // 建堆
    private void heapify() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(i);
        }
    }

    // 返回堆頂元素
    public int peek() {
        return array[0];
    }

    // 刪除堆頂元素
    public int poll() {
        int top = array[0];
        swap(0, size - 1);
        size--;
        down(0);
        return top;
    }

    // 刪除指定元素
    public int poll(int index) {
        int temp = array[index];
        swap(index, size - 1);
        size--;
        down(index);
        return temp;
    }

    // 替換堆頂元素
    public void replace(int element) {
        array[0] = element;
        down(0);
    }

    // 添加元素至尾部
    public void offer(int element) {
        if (size == array.length) {
            // 擴容
            grow();
        }
        up(element);
        size++;
    }

    private void grow() {
        int capacity = size + (size >> 1);
        int[] newArray = new int[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    // 將element上移
    private void up(int element) {
        int child = size;
        while (child > 0) {
            int parent = (child - 1) / 2;
            boolean cmp = max ? element > array[parent] : element < array[parent];
            if (cmp) {
                array[child] = array[parent];
            } else {
                break;
            }
            child = parent;
        }
        array[child] = element;
    }

    // 將parent索引處的元素下移 parent = size/2-1
    private void down(int parent) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int maxOrMin = parent;
        if (left < size && (max ? array[left] > array[maxOrMin] : array[left] < array[maxOrMin])) {
            maxOrMin = left;
        }
        if (right < size && (max ? array[right] > array[maxOrMin] : array[right] < array[maxOrMin])) {
            maxOrMin = right;
        }
        if (maxOrMin != parent) {
            swap(maxOrMin, parent);
            down(maxOrMin);
        }
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
