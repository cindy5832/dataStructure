package com.datastructure.demo.sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSortHore {
    /**
     * 雙邊循環快排 (Lomuto分區方案)
     * 每輪找到一個基準點元素，將比她小的放到其左邊，大的放右邊，稱為分區
     * 1. 選擇左元素作為基準點元素
     * 2. j 指針負責找到比基準點小的元素，i 找比基準點大的元素，一旦找到兩者進行交換
     * - j 從左向右
     * - i 從右向左
     * 4. 最後基準點與i交換，i即為基準點最終位置
     **/

    private static void sort(int[] a) {
        quick(a, 0, a.length - 1);
    }

    private static void quick(int[] a, int left, int right) {
        if (left >= right) return;
        int p = partition(a, left, right); // 基準點元素的索引
        quick(a, left, p - 1);
        quick(a, p + 1, right);
    }

    private static int partition(int[] a, int left, int right) {
        // 隨機元素作為基準點
        int idx = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
        swap(a, idx, left);
        int pv = a[left]; // 基準點元素值
        int i = left;
        int j = right;
        while (i < j) {
            // 1. j 從右向左找比基準點小的值
            while (a[j] > pv && i < j) {
                j--;
            }
            // 2. i 從左向右找比基準點大的值
            while (a[i] <= pv && i < j) {
                i++;
            }
            // 3. 交換位置
            swap(a, i, j);
        }
        swap(a, left, i);
        return i;
    }

    private static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {5, 3, 7, 2, 9, 8, 1, 4};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
