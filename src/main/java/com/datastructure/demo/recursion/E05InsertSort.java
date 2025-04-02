package com.datastructure.demo.recursion;

public class E05InsertSort {
    public static void sort(int[] a) {
        insertion(a, 1);
    }

    private static void insertion(int[] a, int low) {
        if (low == a.length) return;
        int t = a[low];
        int i = low - 1; // 已排序區域指針

        while (i >= 0 && a[i] > t) { // 沒有找到插入位置
            a[i + 1] = a[i];
            i--;
        }
        if (i + 1 != low) {
            a[i + 1] = t;
        }
        // 找到插入位置
        insertion(a, low + 1);
    }

    // 複製次數較多
    private static void insertion2(int[] a, int low) {
        if (low == a.length) {
            return;
        }
        int i = low - 1;
        while (i >= 0 && a[i] > a[i + 1]) {
            int t = a[i];
            a[i] = a[i + 1];
            a[i + 1] = t;
            i--;
        }
        insertion(a, low + 1);
    }
}
