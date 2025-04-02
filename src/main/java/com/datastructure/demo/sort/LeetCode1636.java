package com.datastructure.demo.sort;

import java.util.*;

public class LeetCode1636 {
    public int[] frequencySort1(int[] nums) {
        Map<Integer, Integer> map = new TreeMap<>();
        int[] result = new int[nums.length];
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return Arrays.stream(nums).boxed().sorted(
                (a, b) -> {
                    if (map.get(a) < map.get(b)) {
                        return -1;
                    } else if (map.get(a) > map.get(b)) {
                        return 1;
                    } else {
                        return b - a;
                    }
                }
        ).mapToInt(Integer::intValue).toArray();
    }

    public int[] frequencySort(int[] nums) {
        int[] count = new int[201];
        for (int i : nums) {
            count[i + 100]++;
        }
        // 比較器 按頻率升序 再按數值降序
        return Arrays.stream(nums).boxed().sorted((a, b) -> {
            int af = count[a + 100];
            int bf = count[b + 100];
            if (af < bf) {
                return -1;
            } else if (af > bf) {
                return 1;
            } else {
                return b - a; // 降序
            }
        }).mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        // Input: nums = [-1,1,-6,4,5,-6,1,4,1]
        // Output: [5,-1,4,4,-6,-6,1,1,1]
        int[] nums = {2, 3, 1, 3, 2};
        LeetCode1636 leetcode1636 = new LeetCode1636();
        int[] result = leetcode1636.frequencySort1(nums);
        System.out.println(Arrays.toString(result)); // 1,3,3,2,2
    }
}
