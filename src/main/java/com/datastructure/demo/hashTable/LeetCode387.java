package com.datastructure.demo.hashTable;

import java.util.HashMap;

public class LeetCode387 {
    public int firstUniqChar(String s) {
        int[] array = new int[26];
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            array[ch - 97]++;
        }
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (array[ch - 97] == 1) {
                return i;
            }
        }
        return -1;
    }

    public int firstUniqChar1(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        System.out.println(new LeetCode387().firstUniqChar1("aabb"));
    }
}
