package com.datastructure.demo.divideAndConquer;

public class FindKthLargestLeetCode215 {
    public int findKthLargest(int[] nums, int k) {
        return QuickSelect.quick(nums, 0, nums.length - 1, nums.length - k);
    }

    public static void main(String[] args) {
        int[] array = {6, 5, 1, 2, 4};
        FindKthLargestLeetCode215 code = new FindKthLargestLeetCode215();
        System.out.println(code.findKthLargest(array, 2));
    }
}
