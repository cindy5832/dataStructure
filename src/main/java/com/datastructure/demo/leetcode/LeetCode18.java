package com.datastructure.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetCode18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        dfs(4, 0, nums.length - 1, 0, nums, new LinkedList<>(), result);
        return result;
    }

    private void dfs(int n, int i, int j, long target, int[] nums, LinkedList<Integer> stack, List<List<Integer>> result) {
        if (n == 2) {
            // 兩數和
            twoSum(i, j, nums, target, stack, result);
            return;
        }
        for (int k = i; k < j - (n - 2); k++) {
            // 檢查重複
            if (k > i && nums[k] == nums[k - 1]) {
                continue;
            }
            // 固定一個數字，在try n-1個數字之和
            stack.push(nums[k]);
            dfs(n - 1, k + 1, j, target - nums[k], nums, stack, result);
            stack.pop();
        }
    }

    public void twoSum(int i, int j, int[] numbers, long target, LinkedList<Integer> stack, List<List<Integer>> result) {
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                ArrayList<Integer> list = new ArrayList<>(stack);
                list.add(numbers[i]);
                list.add(numbers[j]);
                result.add(list);
                // 繼續查找
                i++;
                j--;
                while (i < j && numbers[i] == numbers[i - 1]) {
                    i++;
                }
                while (i < j && numbers[j] == numbers[j + 1]) {
                    j--;
                }
            }
        }
    }

    public static void main(String[] args) {
        LeetCode18 leetCode18 = new LeetCode18();
        List<List<Integer>> list = leetCode18.fourSum(new int[]{1000000000,1000000000,1000000000,1000000000}, -294967296);
        System.out.println(list);
    }
}
