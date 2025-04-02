package com.datastructure.demo.sort;

import com.datastructure.demo.linkedList.DynamicArray;

import java.util.Arrays;

public class BucketSort {
    public static void main(String[] args) {
        int[] ages = {20, 19, 28, 66, 25, 31, 67, 30}; // age 1~99
        System.out.println(Arrays.toString(ages));
        sort(ages);
        System.out.println(Arrays.toString(ages));
    }

    static void sort(int[] ages) {
        // 準備桶
        DynamicArray[] bucket = new DynamicArray[10];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new DynamicArray();
        }
        // 放入年齡數據
        for (int age : ages) {
            bucket[age / 10].addLast(age);
        }
        int k = 0;
        for(DynamicArray bucket1: bucket){
//            System.out.println(Arrays.toString(bucket1.stream().toArray()));
            int[] array = bucket1.stream().toArray();
            InsertionSort.sort(array);
            System.out.println(Arrays.toString(array));
            for(int v : array){
                ages[k++] = v;
            }
        }
    }
}
