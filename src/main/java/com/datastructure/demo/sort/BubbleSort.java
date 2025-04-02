package com.datastructure.demo.sort;

import java.util.Arrays;

public class BubbleSort {

    // 每輪冒泡不段的比較相鄰的兩個元素，若是逆序，則交換他們的位置
    // 下一輪冒泡，可以調整未排序的右邊界，減少不必要比較
    // 優化：每次循環時，若能確定更適合的右邊界，則可減少冒泡輪數
    private static void bubble(int[] a) {
        int j = a.length - 1;
        do {
            int x = 0;
            for (int i = 0; i < j; i++) {
                if (a[i] > a[i + 1]) {
                    int t = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = t;
                    x = i;
                }
            }
            j = x;
        } while (j != 0);
    }

    public static void main(String[] args) {
        int[] a = {6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(a));
        bubble(a);
        System.out.println(Arrays.toString(a));
    }
}
