package com.datastructure.demo.divideAndConquer;

// 平方根整數部分
public class SqrtLeetCode69 {
    static int mySqrt(int x) {
        long i = 1;
        long j = x;
        long r = 0;
        while (i <= j) {
            long m = (i + j) >>> 1;
            long mm = m * m;
            if (mm == x) {
                return (int)m;
            } else if (mm < x) {
                i = m + 1;
                r = m;
            } else {
                j = m - 1;
            }
        }
        return (int)r;
    }

    public int mySqrt1(int x) {
        int r = 0;
        int i = 1;
        int j = x;
        while (i <= j) {
            int m = (i + j) >>> 1;
            int mm = m * m;
            if (mm == x) {
                return m;
            } else if (mm < x) {
                i = m + 1;
                r = m;
            } else {
                j = m - 1;
            }
        }
        return r;
    }

    public static void main(String[] args) {
//        System.out.println(mySqrt(3)); // 1
//        System.out.println(mySqrt(1)); // 1
//        System.out.println(mySqrt(2)); // 1
//        System.out.println(mySqrt(4)); // 2
//        System.out.println(mySqrt(8)); // 2
//        System.out.println(mySqrt(9)); // 3
        System.out.println(mySqrt(2147395599)); // 2147395599
//        System.out.println(new SqrtLeetCode69().mySqrt1(3));
    }
}
