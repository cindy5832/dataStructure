package com.datastructure.demo.hashTable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class LeetCode819 {
    // 出現次數最多的單詞
    public String mostCommonWord1(String paragraph, String[] banned) {
        String[] split = paragraph.toLowerCase().split("[^A-Za-z]+");
        HashMap<String, Integer> map = new HashMap<>();
        Set<String> set = Set.of(banned);
        for (String s : split) {
//            if (map.containsKey(s)) {
//                map.put(s, map.get(s) + 1);
//            } else {
//                map.put(s, 1);
//            }
            if (!set.contains(s)) {
                map.compute(s, (k, v) -> v == null ? 1 : v + 1);
            }
        }
        Optional<Map.Entry<String, Integer>> optional = map.entrySet().stream().max(Map.Entry.comparingByValue());
        return optional.map(Map.Entry::getKey).orElse(null);
    }

    public String mostCommonWord2(String paragraph, String[] banned) {
        String[] split = paragraph.toLowerCase().split("[^A-Za-z]+");
        HashMap<String, Integer> map = new HashMap<>();
        Set<String> set = Set.of(banned);
        for (String s : split) {
            if (!set.contains(s)) {
                map.compute(s, (k, v) -> v == null ? 1 : v + 1);
            }
        }
        int max = 0;
        String maxKey = null;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            if (value > max) {
                max = value;
                maxKey = entry.getKey();
            }
        }
        return maxKey;
    }

    public String mostCommonWord3(String paragraph, String[] banned) {

        HashMap<String, Integer> map = new HashMap<>();
        Set<String> set = Set.of(banned);
        char[] chars = paragraph.toLowerCase().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (c >= 'a' && c <= 'z') {
                sb.append(c);
            } else {
                String key = sb.toString();
                if (!set.contains(key)) {
                    map.compute(key, (k, v) -> v == null ? 1 : v + 1);
                }
                sb = new StringBuilder();
            }
        }
        if (sb.length() > 0) {
            String key = sb.toString();
            map.compute(key, (k, v) -> v == null ? 1 : v + 1);
        }
        int max = 0;
        String maxKey = null;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            if (value > max) {
                max = value;
                maxKey = entry.getKey();
            }
        }
        return maxKey;
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        HashMap<String, Integer> map = new HashMap<>();
        Set<String> set = Set.of(banned);
        char[] chars = paragraph.toLowerCase().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (c >= 'a' && c <= 'z') {
                sb.append(c);
            } else {
                if (!set.contains(sb.toString()) && !sb.isEmpty()) {
                    map.compute(sb.toString(), (k, v) -> v == null ? 1 : v + 1);
                }
                sb.setLength(0);
            }
        }
        if (!sb.isEmpty()) {
            if (!set.contains(sb.toString())) {
                map.compute(sb.toString(), (k, v) -> v == null ? 1 : v + 1);
            }
        }
        int max = 0;
        String maxKey = null;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                maxKey = entry.getKey();
            }
        }
        return maxKey;
    }

    public static void main(String[] args) {
        String[] banned = {"bob", "hit"};
        String s = new LeetCode819().mostCommonWord("Bob. hIt, baLl", banned);
        System.out.println(s);
    }
}
