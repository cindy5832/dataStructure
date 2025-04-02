package com.datastructure.demo.graph;

import java.util.Arrays;

// 不相交集合 (並查集合)
public class DisjointSetUnionBySize {
    int[] s;
    int[] size;

    public DisjointSetUnionBySize(int size) {
        this.s = new int[size];
        this.size = new int[size];
        for (int i = 0; i < size; i++) {
            s[i] = i;
            this.size[i] = 1;
        }
    }

    // find 找到老大
    public int find(int x) {
//        if(s[x] < 0) return x;
//        else return find(s[x]);
        if (x == s[x]) return x;
        return s[x] = find(s[x]);
    }

    // union 是讓兩個集合"相交"，即選出新老大 - x, y是原老大
    public void union(int x, int y) {
        if (size[x] < size[y]) {
            s[x] = y;
            size[y] = size[x] + size[y];
        } else {
            s[y] = x;
            size[x] = size[x] + size[y]; // 更新老大元素個數
        }
        if(size[x] < size[y]){
            size[x] += size[y]; // 跟新老大元素個數
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(s);
    }

    public static void main(String[] args) {
        // 索引對應頂點
        // 元素是用來表示與之有關係的頂點
        /**
         * 索引  0  1  2  3  4  5  6
         * 元素 [0, 1, 2, 3, 4, 5, 6] 表示一開始頂點直接沒有聯繫 (只與自己有聯繫)
         **/
        DisjointSetUnionBySize set = new DisjointSetUnionBySize(7);
        System.out.println(set);
        set.union(0, 1);
        set.union(1, 2);
        set.union(2, 3);
        set.union(3, 4);
        set.union(4, 5);
        set.union(5, 6);
        System.out.println(set);
    }
}
