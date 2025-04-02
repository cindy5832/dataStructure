package com.datastructure.demo.leetcode;

import java.util.Arrays;
import java.util.Random;

public class SkipListLeetCode1206 {
    public static void main(String[] args) {
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < 1000; i++) {
//            int level = randomLevel(4);
//            map.compute(level, (k, v) -> v == null ? 1 : v + 1);
//        }
//        System.out.println(map.entrySet().stream().collect(
//                Collectors.toMap(
//                        Map.Entry::getKey,
//                        e -> String.format("%d%%", e.getValue() * 100 / 1000)
//                )
//        ));

        SkipList list = new SkipList();
        SkipList.Node head = list.head;
        SkipList.Node n3 = new SkipList.Node(3);
        SkipList.Node n7 = new SkipList.Node(7);
        SkipList.Node n11 = new SkipList.Node(11);
        SkipList.Node n12 = new SkipList.Node(12);
        SkipList.Node n16 = new SkipList.Node(16);
        SkipList.Node n19 = new SkipList.Node(19);
        SkipList.Node n22 = new SkipList.Node(22);
        SkipList.Node n23 = new SkipList.Node(23);
        SkipList.Node n26 = new SkipList.Node(26);
        SkipList.Node n30 = new SkipList.Node(30);
        SkipList.Node n37 = new SkipList.Node(37);
        head.next[0] = head.next[1] = head.next[2] = n3;
        head.next[3] = head.next[4] = head.next[5] = head.next[6] = head.next[7] = n19;
        n3.next[0] = n3.next[1] = n3.next[2] = n7;
        n7.next[0] = n11;
        n7.next[1] = n12;
        n7.next[2] = n16;
        n11.next[0] = n12;
        n12.next[0] = n12.next[1] = n16;
        n16.next[0] = n16.next[1] = n16.next[2] = n19;
        n19.next[0] = n19.next[1] = n19.next[2] = n22;
        n19.next[3] = n26;
        n22.next[0] = n23;
        n22.next[1] = n22.next[2] = n26;
        n23.next[0] = n26;
        n26.next[0] = n30;
        n26.next[1] = n37;
        n30.next[0] = n37;
        System.out.println(Arrays.toString(list.find(30)));
    }

    static Random r = new Random();

    /**
     * 隨機返回1~max的數字
     * 從1開始，數字的機率逐漸減半，如 max = 4
     * - 50% 機率返回1
     * - 25% 機率返回2
     * - 12.5% 機率返回3
     * - 12.5% 機率返回4
     **/
    static int randomLevel(int max) {
//        return r.nextBoolean() ? 1 : r.nextBoolean() ? 2 : r.nextBoolean() ? 3 : 4;
        int x = 1;
        while (x < max) {
            if (r.nextBoolean()) {
                return x;
            }
            x++;
        }
        return x;
    }

    /**
     * 規則：
     * 若next 指針為 null 或者next指向的節點值 >= 目標，向下找
     * 若next 指針不為 null，且 next指向的節點值 < 目標，向右找
     **/
    static class SkipList {
        private static final int MAX = 10;  // redis 32 java 62
        private final Node head = new Node(-1);

        static class Node {
            int val;
            Node[] next = new Node[MAX]; // next 數組

            public Node(int val) {
                this.val = val;
            }

            @Override
            public String toString() {
                return "Node:" + val;
            }
        }

        public Node[] find(int val) {
            Node[] path = new Node[MAX];
            Node curr = head;
            for (int level = MAX - 1; level >= 0; level--) { // 從上向下找
                while (curr.next[level] != null && curr.next[level].val < val) { // 向右找
                    curr = curr.next[level];
                }
                path[level] = curr;
            }
            return path;
        }

        public boolean search(int val) {
            Node[] path = find(val);
            Node node = path[0].next[0];
            return node != null && node.val == val;
        }

        public void add(int val) {
            // 1. 確定添加位置 (把val當作目標查詢，經歷路徑可確認添加位置)
            Node[] path = find(val);
            // 2. 創建心結點隨機高度
            Node node = new Node(val);
            int level = randomLevel(MAX);
            // 3. 修改路徑節點 next 指針，以及新節點 next 指針
            for (int i = 0; i < level; i++) {
                node.next[i] = path[i].next[i];
                path[i].next[i] = node;
            }
        }

        public boolean erase(int val) {
            Node[] path = find(val);
            Node node = path[0].next[0];
            if (node == null || node.val != val) {
                return false;
            }
            for (int i = 0; i < MAX; i++) {
                if (path[i].next[i] != node) {
                    break;
                }
                path[i].next[i] = node.next[i];
            }
            return true;
        }
    }
}
