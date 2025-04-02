package com.datastructure.demo.leetcode;

public class LeetCode167 {
    /**
     * 雙指針 i 和 j 分別指向最左側和最右側的數字
     * 兩指向的數字和target相比
     * > target i++ 向右找
     * < target i-- 向左找
     **/
    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                break;
            }
        }
        return new int[]{i + 1, j + 1};
    }
}
