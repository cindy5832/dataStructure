package com.datastructure.demo.heap;

import java.util.Arrays;

// 數據流中的中位數
// 若為奇數個數返回中間值；若為偶數返回中間兩數的平均值
// 使用大小頂堆
public class LeetCode295 {
    /**
     * 為了保證兩邊數量的平衡
     * - 兩邊個數一樣時，左邊個數+1
     * - 兩邊個數不同時，右邊個數+1
     * 加入方法
     * - 左邊個數+1時，新元素添加在右邊，將右邊最小的值彈出加到左邊
     * - 右邊個數+1時，新元素添加在左邊，將左邊最小的值彈出加到右邊
     **/
    private Heap left = new Heap(10, true);
    private Heap right = new Heap(10, false);

    @Override
    public String toString() {
        int size = left.size;
        int[] left = new int[size];
        for (int i = 0; i < size; i++) {
            left[size - 1 - i] = this.left.array[i];
        }
        int[] right = Arrays.copyOf(this.right.array, this.right.size);
        return Arrays.toString(left) + "<->" + Arrays.toString(right);
    }

    public void addNum(int num) {
        if (left.getSize() == right.getSize()) {
            right.offer(num);
            int min = right.poll();
            left.offer(min);
        } else {
            left.offer(num);
            right.offer(left.poll());
        }
    }

    // 雙數 取堆頂元素平均
    // 單數 取左邊堆頂元素
    public double findMedian() {
        if (right.getSize() == left.getSize()) {
            return (left.peek() + right.peek()) / 2.0;
        } else {
            return left.peek();
        }
    }

    public static void main(String[] args) {
        LeetCode295 leet = new LeetCode295();
        leet.addNum(1);
        System.out.println(leet.toString());
        leet.addNum(2);
        System.out.println(leet.toString());
        leet.addNum(3);
        System.out.println(leet.toString());
        leet.addNum(7);
        System.out.println(leet.toString());
        leet.addNum(8);
        System.out.println(leet.toString());
        leet.addNum(9);
        System.out.println(leet.toString());
    }
}
