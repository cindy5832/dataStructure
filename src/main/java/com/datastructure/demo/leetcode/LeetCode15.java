package com.datastructure.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetCode15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        dfs(3, 0, nums.length - 1, 0, nums, new LinkedList<>(), result);
        return result;
    }

    private void dfs(int n, int i, int j, int target, int[] nums, LinkedList<Integer> stack, List<List<Integer>> result) {
        if (n == 2) {
            // 兩數和
            twoSum(i, j, nums, target, stack, result);
            return;
        }
        for (int k = i; k < j; k++) {
            // 檢查重複
            if (k >= 1 && nums[k] == nums[k - 1]) {
                continue;
            }
            // 固定一個數字，在try n-1個數字之和
            stack.push(nums[k]);
            dfs(n - 1, k + 1, j, target - nums[k], nums, stack, result);
            stack.pop();
        }
    }

    public void twoSum(int i, int j, int[] numbers, int target, LinkedList<Integer> stack, List<List<Integer>> result) {
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

    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }
                    j++;
                    k--;
                } else if (sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode15 leetCode15 = new LeetCode15();
        List<List<Integer>> result = leetCode15.threeSum1(new int[]{-1, 0, 1, 2, -1, -4}); // [[-1,-1,2],[-1,0,1]]
        List<List<Integer>> result1 = leetCode15.threeSum1(new int[]{0, 0, 0, 0});
        System.out.println(result1);
    }
}
