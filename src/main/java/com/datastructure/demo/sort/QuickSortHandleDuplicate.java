package com.datastructure.demo.sort;

import java.util.Arrays;

public class QuickSortHandleDuplicate {

    private static void sort(int[] a) {
        quick(a, 0, a.length - 1);
    }

    private static void quick(int[] a, int left, int right) {
        if (left >= right) return;
        int p = partition(a, left, right); // 基準點元素的索引
        quick(a, left, p - 1);
        quick(a, p + 1, right);
    }

    /**
     * 循環內
     * i 從 left + 1 開始，從左向右找大的或相等的
     * j 從 right 開始，從右向左找小的或相等的
     * 交換 i++ j--
     * 循環外 j 和 基準點交換，j 即為分區位置
     **/
    private static int partition(int[] a, int left, int right) {
        int pv = a[left]; // 基準點元素值
        int i = left + 1;
        int j = right;
        while (i <= j) {
            while (i <= j && a[i] < pv) {
                i++;
            }
            while (i <= j && a[j] > pv) {
                j--;
            }
            if (i <= j) {
                swap(a, i, j);
                i++;
                j--;
            }
        }
        swap(a, j, left);
        return j;
    }

    private static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {4,2,1,3,2,4};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
