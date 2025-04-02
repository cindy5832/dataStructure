package com.datastructure.demo.divideAndConquer;

public class FindMedian {
    public static double findMedian(int[] nums) {
        if (nums.length % 2 == 1) {
            return QuickSelect.quick(nums, 0, nums.length - 1, nums.length / 2);
        } else {
            int x = QuickSelect.quick(nums, 0, nums.length - 1, nums.length / 2);
            int y = QuickSelect.quick(nums, 0, nums.length - 1, nums.length / 2 - 1);
            return (x + y) / 2.0;
        }

    }

    public static void main(String[] args) {
        System.out.println("奇數");
        System.out.println(findMedian(new int[]{4, 5, 1}));
        System.out.println("偶數");
        System.out.println(findMedian(new int[]{3, 4, 5, 1}));
    }
}
