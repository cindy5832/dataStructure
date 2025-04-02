package com.datastructure.demo.graph;

import java.util.Arrays;

// 不相交集合 (並查集合)
public class DisjointSet {
    int[] s;

    public DisjointSet(int size) {
        this.s = new int[size];
//        Arrays.fill(s, -1);
        for (int i = 0; i < size; i++) {
            s[i] = i;
        }
    }

    // find 找到老大 // 優化：路徑壓縮
    public int find(int x) {
//        if(s[x] < 0) return x;
//        else return find(s[x]);
        if(x == s[x]) return x;
        return s[x] = find(s[x]);
    }
    // union 是讓兩個集合"相交"，即選出新老大 - x, y是原老大
    public void union(int x, int y) {
        s[y] = x;
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
        DisjointSet set = new DisjointSet(7);
        System.out.println(set);
        set.union(0,1);
        set.union(1,2);
        set.union(2,3);
        set.union(3,4);
        set.union(4,5);
        set.union(5,6);
        System.out.println(set);
    }
}
