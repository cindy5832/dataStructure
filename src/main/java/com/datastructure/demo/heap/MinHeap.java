package com.datastructure.demo.heap;

import java.util.Arrays;

// 建堆
public class MinHeap {
    int[] array;
    int size;

    public MinHeap(int capacity) {
        this.array = new int[capacity];
    }

    public MinHeap(int[] array) {
        this.array = array;
        this.size = array.length;
        heapify();
    }

    // 建堆
    private void heapify() {
        // 找到最後葉子節點 size/2 -1
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(i);
        }
    }

    public boolean isFull(){
        return size == array.length;
    }

    // 獲取堆頂元素 - 索引0的位置
    public int peak() {
        if (array == null) {
            return -1;
        }
        return array[0];
    }

    // 刪除堆頂元素 返回堆頂元素
    public int poll() {
        if (array == null) {
            return -1;
        }
        int top = array[0];
        swap(0, size - 1);
        size--;
        down(0);
        return top;
    }

    /* 刪除指定元素
        index = 索引
        return = 被刪除元素
     */
    public int poll(int index) {
        if (array == null) {
            return -1;
        }
        int temp = array[index];
        swap(index, size - 1);
        size--;
        down(index);
        return temp;
    }

    // 替換堆頂元素
    public void replace(int replaced) {
        array[0] = replaced;
        down(0);
    }

    // 堆的尾部添加元素 element=待添加元素
    public boolean offer(int element) {
        if(array == null){
            array[0] = element;
            return true;
        }
        up(element);
        size++;
        return true;
    }

    // 將element元素上浮
    private void up(int element) {
        int child = size;
        while (child > 0) {
            int parent = (child - 1) / 2;
            if (element < array[parent]) {
                array[child] = array[parent];
            } else {
                break;
            }
            child = parent;
        }
        array[child] = element;
    }

    // 將parent索引處的元素下潛：與兩個較大的孩子者交換，直到沒孩子或孩子沒它大
    private void down(int parent) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int min = parent;
        if (left < size && array[left] < array[min]) {
            min = left;
        }
        if (right < size && array[right] < array[min]) {
            min = right;
        }
        if (min != parent) { // 找到更大的孩子
            swap(min, parent);
            down(min);
        }
    }

    // 交換兩個索引的元素
    private void swap(int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static void main(String[] args) {
        int[] array = {2, 3, 1, 7, 6, 4, 5};
        MinHeap maxHeap = new MinHeap(array);
        System.out.println(Arrays.toString(maxHeap.array));

        // 堆排序
        while (maxHeap.size > 1){
            maxHeap.swap(0, maxHeap.size-1);
            maxHeap.size--;
            maxHeap.down(0);
        }
        System.out.println(Arrays.toString(maxHeap.array));
    }
}
