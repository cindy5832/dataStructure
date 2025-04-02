package com.datastructure.demo.hashTable;

import java.util.HashMap;
import java.util.HashSet;

public class LeetCode217 {
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int key : nums) {
            if (map.containsKey(key)) return true;
            map.put(key, key);
        }
        return false;
    }

    public boolean containsDuplicate1(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int key : nums) {
            if(!set.add(key)) return true;
        }
        return false;
    }
}
