package com.datastructure.demo.binarySearch;

public class BinarySearch {
    /**
     * @return int 找到返回索引，找不到則返回-1
     * @description: 二分查找
     * @author: ${USER}
     * @date: ${DATE}
     * @param: [a, target] a=待查找的升序數組 target=待查找的目標值
     **/
    public static int binarySearchBasic(int[] a, int target) {
        int i = 0, j = a.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1; // 若用(i+J)/2 可能會有超過int最大值的可能，導致顯示為負數 (二進制第一個為符號位) // 通用於不同語言
            if (target < a[m]) {
                j = m - 1;
            } else if (a[m] < target) {
                i = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    /**
     * @return int
     * @description: TODO
     * @author: ${USER}
     * @date: ${DATE}
     * @param: [a, target]
     **/
    public static int binarySearchAlternative(int[] a, int target) {
        int i = 0, j = a.length;    // first difference j作為邊界看，不參與比較
        while (i < j) {             // second difference
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m;              // third difference
            } else if (a[m] < target) {
                i = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    /**
     * @return int
     * @description: 循環內的比較次數較少，但時間複雜度較高
     * @author: ${USER}
     * @date: ${DATE}
     * @param: [a, target]
     **/
    public static int binarySearch1(int[] a, int target) {
        int i = 0, j = a.length;
        while (1 < j - i) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m;
            } else {
                i = m;
            }
        }
        if (a[i] == target) {
            return i;
        } else {
            return -1;
        }
    }

    /**
     * @return int 找到返回最左索引 找不到返回-1
     * @description: 二分查找 Leftmost
     * @author: ${USER}
     * @date: ${DATE}
     * @param: [a, target] a = 待查找的升序數組 target = 待查找的目標
     **/
    public static int binarySearchLeftmost1(int[] a, int target) {
        int i = 0, j = a.length - 1;
        int candicate = -1; // 候選者
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if (a[m] < target) {
                i = m + 1;
            } else {
                candicate = m; // 紀錄候選者
                j = m - 1;
            }
        }
        return candicate;
    }

    /**
     * @return int 返回>= target的最靠左索引
     * @description: 二分查找 Leftmost
     * @author: ${USER}
     * @date: ${DATE}
     * @param: [a, target]
     **/
    public static int binarySearchLeftmost2(int[] a, int target) {
        int i = 0, j = a.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target <= a[m]) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return i;
    }

    /**
     * @return int int 找到返回最右索引 找不到返回-1
     * @description: 二分查找 RightMost
     * @author: ${USER}
     * @date: ${DATE}
     * @param: [a, target]
     **/
    public static int binarySearchRightMost1(int[] a, int target) {
        int i = 0, j = a.length - 1;
        int candicate = -1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if (a[m] < target) {
                i = m + 1;
            } else {
                candicate = m;
                i = m + 1;
            }
        }
        return candicate;
    }

    /**
     * @return int 返回<= target的最靠右索引值
     * @description: 二分查找 RightMost
     * @author: ${USER}
     * @date: ${DATE}
     * @param: [a, target]
     **/
    public static int binarySearchRightMost2(int[] a, int target) {
        int i = 0, j = a.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return i - 1;
    }

}
