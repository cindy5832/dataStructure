package com.datastructure.demo.linkedList;

import java.util.Arrays;

// LeetCode 88
public class MergeSortedArray {
    /**
     * @return void
     * @description: 遞歸法 Method 1
     * @param: [a1, i, ie, j, je, a2, k] a1 原始數組 a2 結果數組 i ie第一個有序區間的起始終點 j je第二個有序數組的起始終點
     **/
    public static void merge1(int[] a1, int i, int ie, int j, int je, int[] a2, int k) {
        if (i > ie) {
            System.arraycopy(a1, j, a2, k, je - j + 1);
            return;
        }
        if (j > je) {
            System.arraycopy(a1, i, a2, k, ie - i + 1);
            return;
        }
        if (a1[i] < a1[j]) {
            a2[k] = a1[i];
            merge1(a1, i + 1, ie, j, je, a2, k + 1);
        } else {
            a2[k] = a1[j];
            merge1(a1, i, ie, j + 1, je, a2, k + 1);
        }
    }


    // Method 2
    public static void merge2(int[] a1, int i, int ie, int j, int je, int[] a2) {
        int k = 0;
        while (i <= ie && j <= je) {
            if (a1[i] < a1[j]) {
                a2[k] = a1[i];
                i++;
            } else {
                a2[k] = a1[j];
                j++;
            }
            k++;
        }
        if (i > ie) {
            System.arraycopy(a1, j, a2, k, je - j + 1);
        }
        if (j > je) {
            System.arraycopy(a1, j, a2, k, ie - i + 1);
        }// 複製物件, 從哪複製, 複製到哪, 目標的起始位置, 複製幾個元素
    }

    public static int[] merge3(int[] nums1, int m, int[] nums2, int n) {
        int[] merge = new int[m + n];
        int k = 0;
        int i = 0;
        int j = 0;
        if (n == 0) {
            return nums1;
        } else if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, n);
            return nums1;
        } else {
            while (i < m && j < n) {
                if (nums1[i] < nums2[j]) {
                    merge[k] = nums1[i];
                    i++;
                } else {
                    merge[k] = nums2[j];
                    j++;
                }
                k++;
            }
        }
        if (i >= m) {
            System.arraycopy(nums2, j, merge, k, (m + n) - j - i);
            System.out.println("a===" + Arrays.toString(merge));
        }
        if (j >= n) {
            System.arraycopy(nums1, i, merge, k, (m + n) - j - i);
            System.out.println("b===" + Arrays.toString(merge));
        }
        System.arraycopy(merge, 0, nums1, 0, m + n);
        return nums1;
    }

    public static void main(String[] args) {
        int[] a = {1, 5, 6, 2, 4, 10, 11};
        int[] a2 = new int[a.length];
//        merge1(a, 0, 2, 3, 6, a2, 0);
        merge2(a, 0, 2, 3, 6, a2);
        System.out.println(Arrays.toString(a2));

        System.out.println("=======================");
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;
        int[] merge = merge3(nums1, m, nums2, n);
        System.out.println(Arrays.toString(merge));
    }
}

