package com.datastructure.demo.sort;

import java.util.Arrays;

public class QuickSortLomuto {
    /** 單邊循環快排 (Lomuto分區方案)
     * 每輪找到一個基準點元素，將比她小的放到其左邊，大的放右邊，稱為分區
     * 1. 選擇右元素作為基準點元素
     * 2. j 指針負責找到比基準點小的元素，一旦找到則與i進行交換
     * 3. i 指針指向大於基準點小的元素的左邊界，也是每次交換的目標索引
     * 4. 最後基準點與i交換，i即為分區位置
     **/

    private static void sort(int[] a) {
        quick(a, 0, a.length - 1);
    }

    private static void quick(int[] a, int left, int right) {
        if(left >= right) return;
       int p = partition(a, left, right); // 基準點元素的索引
        quick(a, left, p - 1);
        quick(a, p + 1, right);
    }

    private static int partition(int[] a, int left, int right) {
        int pv = a[right]; // 基準點元素值
        int i = left;
        int j = left;
        while (j < right){
            if(a[j] < pv){
                // j 找到比基準點小的
                if(i != j){
                    swap(a, i, j);
                }
                i++;
            }
            j++;
        }
        swap(a, i, right);
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
