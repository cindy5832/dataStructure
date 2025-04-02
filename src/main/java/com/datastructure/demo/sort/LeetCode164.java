package com.datastructure.demo.sort;

import ch.qos.logback.core.joran.sanity.Pair;
import com.datastructure.demo.linkedList.DynamicArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class LeetCode164 {
    public int maximumGap(int[] nums) {
        if (nums.length <= 1) return 0;
        int max = nums[0];
        int min = nums[0];
        for (int i1 = 1; i1 < nums.length; i1++) {
            if (nums[i1] > max) {
                max = nums[i1];
            }
            if (nums[i1] < min) {
                min = nums[i1];
            }
        }
        // 準備桶
        DynamicArray[] bucket = new DynamicArray[(max - min) / 1 + 1];
        for (int i1 = 0; i1 < bucket.length; i1++) {
            bucket[i1] = new DynamicArray();
        }
        // 放入年齡數據
        for (int age : nums) {
            bucket[(age - min) / 1].addLast(age);
        }
        int k = 0;
        for (DynamicArray bucket1 : bucket) {
//            System.out.println(Arrays.toString(bucket1.stream().toArray()));
            int[] array = bucket1.stream().toArray();
            InsertionSort.sort(array);
            System.out.println(Arrays.toString(array));
            for (int v : array) {
                nums[k++] = v;
            }
        }
        System.out.println(Arrays.toString(nums));
        int r = 0;
        for (int i = 1; i < nums.length; i++) {
            r = Math.max(r, nums[i] - nums[i - 1]);
        }
        return r;
    }

    public int maximumGap4(int[] nums) {
        // 1. 處理特殊情況
        if (nums.length <= 1) return 0;
        // 2. 桶排序
        int max = nums[0];
        int min = nums[0];
        for (int i1 = 1; i1 < nums.length; i1++) {
            if (nums[i1] > max) {
                max = nums[i1];
            }
            if (nums[i1] < min) {
                min = nums[i1];
            }
        }
        // 1. 準備桶
        int range = Math.max((max - min) / (nums.length), 1);
        DynamicArray[] bucket = new DynamicArray[(max - min) / range + 1];
        for (int i1 = 0; i1 < bucket.length; i1++) {
            bucket[i1] = new DynamicArray();
        }
        // 2. 放入數據
        for (int a : nums) {
            bucket[(a - min) / range].addLast(a);
        }
        int k = 0;
        for (DynamicArray bucket1 : bucket) {
            // 3. 排序桶內元素
            int[] array = bucket1.stream().toArray();
            InsertionSort.sort(array);
            // 4. 把每個桶排好的內容，依次放入原始數組
            for (int v : array) {
                nums[k++] = v;
            }
        }

        int r = 0;
        for (int i = 1; i < nums.length; i++) {
            r = Math.max(r, nums[i] - nums[i - 1]);
        }
        return r;
    }

    public int maximumGap5(int[] nums) {
        // 1. 處理特殊情況
        if (nums.length <= 1) return 0;
        // 2. 桶排序
        int max = nums[0];
        int min = nums[0];
        for (int i1 = 1; i1 < nums.length; i1++) {
            if (nums[i1] > max) {
                max = nums[i1];
            }
            if (nums[i1] < min) {
                min = nums[i1];
            }
        }
        //
        /**
         * 1. 準備桶
         * 計算桶個數                   期望桶個數
         * (max - min) / range + 1 = nums.length + 1
         * (max - min) / range     = nums.length
         **/
        int range = Math.max((max - min) / (nums.length), 1); // 讓桶個數比元素多一個
        Pair[] bucket = new Pair[(max - min) / range + 1];

        // 2. 放入數據
        for (int a : nums) {
            int index = (a - min) / range;
            if (bucket[index] == null) {
                bucket[index] = new Pair();
            }
            bucket[index].add(a);
        }

        int r = 0;
        int lastMax = bucket[0].max;
        for (int i = 1; i < bucket.length; i++) {
            Pair bucket1 = bucket[i];
            if(bucket1 != null){
                r = Math.max(r, bucket1.min - lastMax);
                lastMax = bucket1.max;
            }
        }
        return r;
    }

    static class Pair { // 桶內的最大最小值
        int max = 0;
        int min = 1000_000_000;

        void add(int v) {
            max = Math.max(v, max);
            min = Math.min(v, min);
        }

        @Override
        public String toString() {
            return "[" + max +
                    ", min=" + min +
                    ']';
        }
    }

    public int maximumGap3(int[] nums) {
        if (nums.length <= 1) return 0;
        // 基數排序
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }
        ArrayList<Integer>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        int m = 1;
        while (m <= max) {
            for (int i : nums) {
                buckets[i / m % 10].add(i);
            }
            int k = 0;
            for (ArrayList<Integer> bucket : buckets) {
                for (Integer i : bucket) {
                    nums[k++] = i;
                }
                bucket.clear();
            }
            m = m * 10;
        }

        int r = 0;
        for (int i = 1; i < nums.length; i++) {
            r = Math.max(r, nums[i] - nums[i - 1]);
        }
        return r;
    }

    public int maximumGap1(int[] nums) {
        if (nums.length <= 1) return 0;
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int first = set.pollFirst();
        int max = 0;
        while (!set.isEmpty()) {
            int next = set.pollFirst();
            max = Math.max(max, next - first);
            first = next;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums1 = {1000, 11, 26, 16, 13};
        int[] nums2 = {10};
        LeetCode164 leetCode164 = new LeetCode164();
        System.out.println(leetCode164.maximumGap5(nums1));
//        System.out.println(leetCode164.maximumGap(nums2));
    }
}
