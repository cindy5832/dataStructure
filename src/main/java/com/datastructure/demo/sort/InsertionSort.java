package com.datastructure.demo.sort;

import java.util.Arrays;

public class InsertionSort {

    public static void sort(int[] arr) {
        for (int low = 1; low < arr.length; low++) {
            int t = arr[low];
            int i = low - 1;
            // 從右向左找插入位置，如果比待插入元素大，則不段右移，空出插入位置
            while (i >= 0 && arr[i] > t) {
                arr[i + 1] = arr[i];
                i--;
            }
            // 找到插入位置
            if (i + 1 != low) {
                arr[i + 1] = t;
            }
        }
    }

    private static void insertion(int[] arr, int low) {
        if (low == arr.length) {
            return;
        }
        int t = arr[low];
        int i = low - 1;
        // 從右向左找插入位置，如果比待插入元素大，則不段右移，空出插入位置
        while (i >= 0 && arr[i] > t) {
            arr[i + 1] = arr[i];
            i--;
        }
        // 找到插入位置
        if (i + 1 != low) {
            arr[i + 1] = t;
        }
        insertion(arr, low + 1);
    }

    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
