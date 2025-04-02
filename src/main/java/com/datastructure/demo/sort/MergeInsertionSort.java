package com.datastructure.demo.sort;

import java.util.Arrays;

public class MergeInsertionSort {
    public static void merge(int[] a1, int i, int iEnd, int j, int jEnd, int[] a2) {
        int k = i;
        while (i <= iEnd && j <= jEnd) {
            if (a1[i] < a1[j]) {
                a2[k] = a1[i];
                i++;
            } else {
                a2[k] = a1[j];
                j++;
            }
            k++;
        }
        if (i > iEnd) {
            System.arraycopy(a1, j, a2, k, jEnd - j + 1);
        }
        if (j > jEnd) {
            System.arraycopy(a1, i, a2, k, iEnd - i + 1);
        }
    }

    public static void sort(int[] a1) {
        int[] a2 = new int[a1.length];
        split(a1, 0, a1.length - 1, a2);
    }

    private static void split(int[] a1, int left, int right, int[] a2) {
        if (right - left <= 32) {
            // 插入排序
            insertionSort(a1, left, right);
            return;
        }
        int m = (left + right) >>> 1;
        split(a1, left, m, a2);
        split(a1, m + 1, right, a2);
        merge(a1, left, m, m + 1, right, a2);
        System.arraycopy(a2, left, a1, left, right - left + 1);
    }

    public static void insertionSort(int[] arr, int left, int right) {
        for (int low = left + 1; low <= right; low++) {
            int t = arr[low];
            int i = low - 1;
            // 從右向左找插入位置，如果比待插入元素大，則不段右移，空出插入位置
            while (i >= left && arr[i] > t) {
                arr[i + 1] = arr[i];
                i--;
            }
            // 找到插入位置
            if (i + 1 != low) {
                arr[i + 1] = t;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 8, 5, 1, 4};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
