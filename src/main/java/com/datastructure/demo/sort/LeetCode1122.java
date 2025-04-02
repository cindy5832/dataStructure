package com.datastructure.demo.sort;

import java.util.Arrays;

public class LeetCode1122 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] count = new int[1001];
        for (int i : arr1) count[i]++;
//        System.out.println(Arrays.toString(count));
        int[] result = new int[arr1.length];
        int k = 0;
        for (int i : arr2) {
            while (count[i] > 0) {
                result[k++] = i;
                count[i]--;
            }
        }
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                result[k++] = i;
                count[i]--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 2, 1, 2, 2, 1, 2, 5, 4};
        int[] arr2 = {2, 3, 1};
        LeetCode1122 leetCode1122 = new LeetCode1122();
        int[] result = leetCode1122.relativeSortArray(arr1, arr2);
        System.out.println(Arrays.toString(result));
    }
}
