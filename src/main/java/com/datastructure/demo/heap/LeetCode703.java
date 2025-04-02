package com.datastructure.demo.heap;

public class LeetCode703 {
    private MinHeap heap;

    public LeetCode703(int k, int[] nums) {
        heap = new MinHeap(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (!heap.isFull()) {
            heap.offer(val);
        } else if (heap.peak() < val) {
            heap.replace(val);
        }
        return heap.peak();
    }

    public static void main(String[] args) {
        LeetCode703 test = new LeetCode703(3, new int[]{4, 5, 8, 2});
        System.out.println(test.add(3));
        System.out.println(test.add(5));
        System.out.println(test.add(10));
        System.out.println(test.add(9));
        System.out.println(test.add(4));
    }
}
