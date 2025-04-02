package com.datastructure.demo.recursion;

import java.util.LinkedList;

public class E02HanoiTower {

    static LinkedList<Integer> a = new LinkedList<>();
    static LinkedList<Integer> b = new LinkedList<>();
    static LinkedList<Integer> c = new LinkedList<>();

    static void init(int n) {
        for (int i = n; i >= 1; i--) {
            a.addLast(i);
        }
    }

    /**
     * @return void
     * @description: TODO
     * @param: [n, a, b, c] n = 圓盤總數 a = 原 b = 暫用 c = 目標位置
     **/
    // step1: 圓盤12 a → b step2: 圓盤3 a → c step3: 圓盤12 b → c
    static void move(int n, LinkedList<Integer> a, LinkedList<Integer> b, LinkedList<Integer> c) {
        if(n == 0) return;
        move(n - 1, a, c, b); // step1
        c.addLast(a.removeLast()); // step2
        print();
        move(n - 1, b, a, c);
    }

    public static void main(String[] args) {
        init(3);
        print();
        move(3,a,b,c);
    }

    private static void print() {
        System.out.println("===========");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
