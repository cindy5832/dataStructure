package com.datastructure.demo.hashTable;

import java.util.*;

public class LeetCode49 {

    // 題目有提strs[i]僅含小寫字母
    /*
     * key = [1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0] 26字母
     * "eat", "tea"
     * key = [2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0] 26字母
     * "eaat", "teaa"
     **/

    static class ArrayKey {
        int[] key = new int[26];

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ArrayKey arrayKey = (ArrayKey) o;
            return Objects.deepEquals(key, arrayKey.key);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(key);
        }

        public ArrayKey(String str) {
            for (char ch : str.toCharArray()) {
                key[ch - 97]++;
            }
        }
    }

    public List<List<String>> groupAnagrams1(String[] strs) {
        HashMap<ArrayKey, List<String>> map = new HashMap<>();
        for (String s : strs) {
            ArrayKey key = new ArrayKey(s);
//            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
//            list.add(s);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(s);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> list = new LeetCode49().groupAnagrams(strs);
        System.out.println(list);
        List<List<String>> list1 = new LeetCode49().groupAnagrams1(strs);
        System.out.println(list1);
    }
}
