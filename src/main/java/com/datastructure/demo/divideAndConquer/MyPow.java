package com.datastructure.demo.divideAndConquer;

public class MyPow {
    static double myPow(double x, long n) {
        long p = n;
        if (p < 0) {
            p = -p;
        }
        double r = myPowPositive(x, p);
        return n < 0 ? 1 / r : r;
    }

    static double myPowPositive(double x, long n) {
        if (n == 0) return 1.0;
        if (n == 1) return x;
        double y = myPowPositive(x, n / 2);
        if ((n & 1) == 0) { // n % 2 == 0 偶數
            return y * y;
        } else { // 奇數
            return x * y * y;
        }
    }

    static double myPow1(double x, long n) {
        double mul = 1;
        for (int i = 0; i < n; i++) {
            mul *= x;
        }
        return mul;
    }

    public static void main(String[] args) {
        System.out.println(myPow(2, -2)); // 66536.0
    }
}
