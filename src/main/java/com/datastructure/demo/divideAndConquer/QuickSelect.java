package com.datastructure.demo.divideAndConquer;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSelect {
    // 求排名第i名的元素，i從0開始，由小到大排
    static int quick(int[] array, int left, int right, int i) {
        int p = partition(array, left, right);
        if (p == i) return array[p];
        if(i < p){ // 往左找
            return quick(array, left, p - 1, i);
        }else{ // 往右找
            return quick(array, p + 1, right, i);
        }
    }

    private static int partition(int[] a, int left, int right) {
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
        int[] array = {6, 5, 1, 2, 4};

        System.out.println(quick(array, 0, array.length - 1, 0)); // 1
        System.out.println(quick(array, 0, array.length - 1, 1)); // 2
        System.out.println(quick(array, 0, array.length - 1, 2)); // 4
        System.out.println(quick(array, 0, array.length - 1, 3)); // 5
        System.out.println(quick(array, 0, array.length - 1, 4)); // 6
    }
}
