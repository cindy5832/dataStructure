package com.datastructure.demo.leetcode;

public class LeetCode14 {
    String longestCommonPrefix(String[] strs) {
        /**
         * 1. 比較某一列時，遇到不同字符，i之前的字符就是解
         * 2. 比較某一列時，遇到字符串長度不夠，i之前的字符就是解
         * 3. i循環自然結束，此時第一個字符串就是解
         **/
        char[] first = strs[0].toCharArray();
        for (int i = 1; i < strs.length; i++) {
            char ch = first[i];
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() == i || ch != strs[j].charAt(i)) {
                    // 情況二 || 情況1
                    return new String(first, 0, i);
                }

            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        LeetCode14 leetcode = new LeetCode14();
        System.out.println(leetcode.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(leetcode.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }
}
