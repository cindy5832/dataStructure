package com.datastructure.demo.recursion;

// 遞歸二分查找
public class E03BinarySearch {
    public static int search(int[] a, int target) {
        return f(a, target, 0, a.length - 1);
    }

    /**
     * @return int 找到返回索引，找不到返回-1
     * @description: 遞歸 (子問題)函數
     * @param: [a, target, i, j] a = 數組, target = 待查找值, i = 起始索引 (包含), j = 結束索引 (包含)
     **/
    private static int f(int[] a, int target, int i, int j) {
        if (i > j) return -1;
        int m = (i + j) >>> 1;
        if (target < a[m]) {
            return f(a, target, i, m = 1);
        } else if (a[m] < target) {
            return f(a, target, m + i, j);
        } else {
            return m;
        }
    }

    public static void main(String[] args) {
        int[] a = {7, 13, 21, 30, 38, 44, 52, 53};
//        System.out.println(search(a, 7));
//        System.out.println(search(a, 21));
//        System.out.println(search(a, 44));
//        System.out.println(search(a, 52));
        System.out.println(search(a, 89));
    }
}
