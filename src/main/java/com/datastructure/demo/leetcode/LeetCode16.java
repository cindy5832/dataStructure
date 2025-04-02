package com.datastructure.demo.leetcode;

import java.util.*;

public class LeetCode16 {
    public int threeSumClosest(int[] nums, int target) {
        int ans = 0;
        int closest = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int distance = 0;
            for (int j = i + 1; j < nums.length - 1; j++) {
                int k = nums.length - 1;
                while (j < k) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum == target) {
                        return sum;
                    } else if (sum < target) {
                        distance = target - sum;
                        closest = Math.min(distance, closest);
                    } else {
                        distance = sum - target;
                        closest = Math.min(distance, closest);
                    }
                    if (closest == distance) ans = sum;
                    k--;
                }
            }
        }
        return ans;
    }
    public int threeSumClosest1(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (Math.abs(target - sum) < Math.abs(target - closestSum)) {
                    closestSum = sum;
                }
                if (sum < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return closestSum;
    }

    public static void main(String[] args) {
        LeetCode16 leetCode16 = new LeetCode16();
        int result = leetCode16.threeSumClosest(new int[]{-1, 2, 1, -4}, 1);
        int result1 = leetCode16.threeSumClosest1(new int[]{-1, 2, 1, -4}, 1);
//        int result = leetCode16.threeSumClosest(new int[]{2, 5, 6, 7}, 16);
        System.out.println(result1);
    }
}
