package com.datastructure.demo.heap;

public class LeetCode215 {
    public static int findKthLargest(int[] nums, int k) {
        MinHeap heap = new MinHeap(k);
        for (int i = 0; i < k; i++) {
            heap.offer(nums[i]);
        }
        for(int i = k; i < nums.length; i++) {
            if(nums[i] > heap.peak()) {
                heap.replace(nums[i]);
            }
        }
        return heap.peak();
    }

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3,2,1,5,6,4}, 2));
        System.out.println(findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    }
}
