package com.datastructure.demo.recursion;

import java.util.Arrays;

/**
 * 遞歸冒泡排序
 * 將數組劃分成2部分(0...j) (j+1 ... a.length-1)
 * 左邊(0...j)是末排序部分
 * 右邊(j+1 ... a.length-1)是已排序部分
 * 未排序區間內，相鄰的兩個元素比較，若前一個大於後一個則交換位置
 **/
public class E04BubbleSort {
    public static void sort(int[] a) {
        bubble(a, a.length - 1);
    }

    // j代表為排序區域右邊界
    private static void bubble(int[] a, int j) {
        if (j == 0) return;
        int x = 0; // 設置一個x值，x右側為以排序完成序列
        for (int i = 0; i < j; i++) {
            if (a[i] > a[i + 1]) {
                int t = a[i];
                a[i] = a[i + 1];
                a[i + 1] = t;
                x = i;
            }
        }
        bubble(a, x);
    }

    public static void main(String[] args) {
        int[] a = {6, 5, 4, 3, 2, 1};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
