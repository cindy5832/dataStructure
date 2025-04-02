package com.datastructure.demo.hashTable;

import java.util.HashSet;

public class LeetCode136 {
    public int singleNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                set.remove(num);
            }
        }
        return set.toArray(new Integer[0])[0];
    }

    /**
     * 1. 任何相同的數字 異或 結果 = 0
     * 2. 任何數字與0 異或 結果是數字本身
     **/
    public static int singleNumber1(int[] nums) {
        int n = nums[0];
        for (int i = 1; i < nums.length; i++){
            n = n ^ nums[i];
            System.out.println(n);
        }
        return n;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,2,4,3};
        singleNumber1(nums);
    }
}
