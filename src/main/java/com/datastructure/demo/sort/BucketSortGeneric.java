package com.datastructure.demo.sort;

import com.datastructure.demo.linkedList.DynamicArray;

import java.util.Arrays;

public class BucketSortGeneric {
    public static void main(String[] args) {
        int[] ages = {9, 0, 5, 1, 3, 2, 4, 6, 8, 7}; // age 1~99
        System.out.println(Arrays.toString(ages));
        int max = ages[0];
        int min = ages[0];
        for (int i = 1; i < ages.length; i++) {
            if (ages[i] > max) {
                max = ages[i];
            }
            if (ages[i] < min) {
                min = ages[i];
            }
        }
        // 準備桶
        DynamicArray[] bucket = new DynamicArray[(max - min) / 3 + 1];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new DynamicArray();
        }
        // 放入年齡數據
        for (int age : ages) {
            bucket[(age - min) / 3].addLast(age);
        }
        int k = 0;
        for (DynamicArray bucket1 : bucket) {
//            System.out.println(Arrays.toString(bucket1.stream().toArray()));
            int[] array = bucket1.stream().toArray();
            InsertionSort.sort(array);
            System.out.println(Arrays.toString(array));
            for (int v : array) {
                ages[k++] = v;
            }
        }
        System.out.println(Arrays.toString(ages));
    }

    static void sort(int[] ages, int range) {
        int max = ages[0];
        int min = ages[0];
        for (int i = 1; i < ages.length; i++) {
            if (ages[i] > max) {
                max = ages[i];
            }
            if (ages[i] < min) {
                min = ages[i];
            }
        }
        // 準備桶
        DynamicArray[] bucket = new DynamicArray[(max - min) / range + 1];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new DynamicArray();
        }
        // 放入年齡數據
        for (int age : ages) {
            bucket[(age - min) / range].addLast(age);
        }
        int k = 0;
        for (DynamicArray bucket1 : bucket) {
//            System.out.println(Arrays.toString(bucket1.stream().toArray()));
            int[] array = bucket1.stream().toArray();
            InsertionSort.sort(array);
            System.out.println(Arrays.toString(array));
            for (int v : array) {
                ages[k++] = v;
            }
        }
    }
}
