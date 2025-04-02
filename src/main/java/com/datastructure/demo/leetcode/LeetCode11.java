package com.datastructure.demo.leetcode;

public class LeetCode11 {
    int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int max = 0;
        while (i < j) {
            if (height[i] < height[j]) {
                int area = (j - i) * height[i];
                max = Math.max(max, area);
                i++;
            } else {
                int area = (j - i) * height[j];
                max = Math.max(max, area);
                j--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LeetCode11 leetCode11 = new LeetCode11();
        System.out.println(leetCode11.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}
