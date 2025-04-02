package com.datastructure.demo.hashTable;

import java.util.Arrays;

public class LeetCode242 {

    /**
     * 輸入 s = "anagram", t = "nagaram"
     * 輸出 true
     * 1. 拿到字符數組，排序後比較字符數組
     * 2. 字符打散放入 int[26] 比較數組
     **/
    public boolean isAnagram(String s, String t) {
        return Arrays.equals(getKey(s), getKey(t));
    }

    private int[] getKey(String s) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i); // 'a' - 97 = 0
            arr[ch - 97]++;
        }
        return arr;
    }

    private int[] getKey1(String s) {
        int[] arr = new int[26];
        char[] chs = s.toCharArray();
        for (char ch : chs) {
            arr[ch - 97]++;
        }
        return arr;
    }

    private int[] getKey2(String s) {
        int[] arr = new int[26];
        for (char ch : s.toCharArray()) {
            arr[ch - 97]++;
        }
        return arr;
    }
}
