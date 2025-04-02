package com.datastructure.demo.sort;

import java.util.Arrays;

public class ShellSort {
    public static void sort(int[] a) {
        for (int gap = a.length >> 1; gap >= 1; gap = gap >> 1) {
            for (int low = gap; low < a.length; low++) {
                int t = a[low];
                int i = low - gap;
                // 自右向左找插入位置，如果比插入元素大，則不段右移，空出插入位置
                while (i >= 0 && t < a[i]) {
                    a[i + gap] = a[i];
                    i-= gap;
                }
                // 找到插入位置
                if (i != low - gap) {
                    a[i + gap] = t;
                }

            }
        }
    }

    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
