package com.datastructure.demo.leetcode;

import javax.naming.spi.DirStateFactory;
import java.util.Arrays;

public class LeetCode76 {
    /**
     * 1. 統計目標串需要各種字符個數，統計原始串 i~j 範圍各種字符個數
     * 2. 如果原始串 i~j 範圍內不滿足條件，j++ 擴大範圍，直到滿足條件 j 停下
     * 3. 一旦滿足條件，i++ 縮小範圍，直到再次不滿足條件
     * 4. 重複 2. 3. 兩步直至 j 到達原始串末尾
     **/
    String minWindow(String s, String t) {
        char[] target = t.toCharArray();
        int[] targetCount = new int[128];
        for (char c : target) {
            targetCount[c]++;
        }
//        System.out.println(Arrays.toString(targetCount));
        int passTotal = 0; // 條件總數
        int passed = 0; // 以通過的條件數

        for (int count : targetCount) {
            if (count > 0) {
                passTotal++;
            }
        }
//        System.out.println("條件總數" + passTotal);

        char[] source = s.toCharArray();
        int[] sourceCount = new int[128];
        int i = 0;
        int j = 0;
        Result res = null;
        while (j < source.length) {
            char right = source[j];
            sourceCount[right]++;
//            System.out.println(right);
            if (sourceCount[right] == targetCount[right]) {
                passed++;
            }
//            System.out.println("處理的string: " + right + "通過數: " + passed);
//            System.out.println("---------------------------------------------");
            // 若滿足所有條件，縮小i範圍，更新範圍內的strung計數和通過條件數
            while (passed == passTotal && i <= j) {
                if (res == null) {
                    res = new Result(i, j);
                } else {
                    if (j - i < res.j - res.i) {
                        res = new Result(i, j);
                    }
                }
                char left = source[i];
                sourceCount[left]--;
                if (sourceCount[left] < targetCount[left]) {
                    passed--;
                }
                i++;
            }
            j++;
        }
        System.out.println(res.i + " " + res.j);
        return res == null ? "" : new String(source, res.i, res.j -res.i +1);
    }

    static class Result {
        int i;
        int j;

        public Result(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) {
        LeetCode76 leetCode76 = new LeetCode76();
        System.out.println(leetCode76.minWindow("ADOBECODEBANC", "ABC")); // BANC
    }
}
