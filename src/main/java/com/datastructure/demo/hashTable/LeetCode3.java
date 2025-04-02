package com.datastructure.demo.hashTable;

import java.util.HashMap;

public class LeetCode3 {

    /**
     * 1. 用begin 和 end 表示string 開始和結束位置
     * 2. 用hash表檢查重複string
     * 3. 從左向右查每個char，若：
     * 1) 沒遇到重複的，調整end
     * 2) 遇到重複，調整begin
     * 3) 將當前string 放到hash table
     * 4. end - begin + 1 是當前string長度
     **/
    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int begin = 0;
        int maxLength = 0;
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                begin = Math.max(begin, map.get(c) + 1);
                map.put(c, end);
            } else {
                map.put(c, end);
            }
             maxLength = Math.max(maxLength, end - begin + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abba";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
