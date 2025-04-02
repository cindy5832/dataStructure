package com.datastructure.demo.hashTable;

import java.util.HashMap;

public class LeetCode1 {

    /**
     * input: nums[2,6,7,x,x,x...], target = 9
     * output: [0,2]
     * thought:
     * 1. 循環遍歷數組，拿到每個數字x
     * 2. 以target-x 作為 key 到hash表查找
     * 1) 若沒找到x為key，索引為value放入hash表
     * 2) 若找到，返回 x 和她配對的索引即可
     **/
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            int y = target - x;
            if (map.containsKey(y)) {
                return new int[]{i, map.get(y)};
            } else {
                map.put(x, i);
            }
        }
        return null;
    }

}
