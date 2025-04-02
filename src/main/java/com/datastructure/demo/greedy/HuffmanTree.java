package com.datastructure.demo.greedy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {
    /**
     * 1. 將統計了出現頻率的的字符，放入優先級隊列
     * 2. 每次出隊列兩個頻率最低的元素，給他們一個爹
     * 3. 將爹放入隊列，重複step 2, step 3
     * 4. 當隊列只剩一個元素時，Huffman 樹構建完成
     **/
    static class Node {
        Character ch; // 字符
        int freq; // 頻率
        Node left;
        Node right;
        String code; // 編碼

        public Node(Character ch) {
            this.ch = ch;
        }

        public Node(int freq, Node left, Node right) {
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "ch=" + ch +
                    ", freq=" + freq +
                    ", left=" + left +
                    ", right=" + right +
                    ", code='" + code + '\'' +
                    '}';
        }

        int getFreq() {
            return freq;
        }

        boolean isLeaf() {
            return left == null;
        }

    }

    String str;
    Map<Character, Node> map = new HashMap<>();
    Node root;

    public HuffmanTree(String str) {
        this.str = str;
        // function 1: 統計頻率
        char[] chars = str.toCharArray();
        for (char c : chars) {
            Node node = map.computeIfAbsent(c, Node::new);
            node.freq++;
        }
        // function 2: 構造樹
        PriorityQueue<Node> queue = new PriorityQueue<>(
                Comparator.comparingInt(Node::getFreq)
        );
        queue.addAll(map.values());
        while (queue.size() >= 2) {
            Node x = queue.poll();
            Node y = queue.poll();
            int freq = x.freq + y.freq;
            queue.offer(new Node(freq, x, y));
        }
        root = queue.poll();
        // function 3: 計算每個字符的編碼
        dfs(root, new StringBuilder());
        for (Node value : map.values()) {
            System.out.println(value + " " + value.code);
        }
    }

    private void dfs(Node node, StringBuilder code) {
        if (node.isLeaf()) {
            // 找到編碼
            node.code = code.toString();
        } else {
            dfs(node.left, code.append("0"));
            dfs(node.right, code.append("1"));
        }
        if (code.length() > 0) {
            code.deleteCharAt(code.length() - 1);
        }
    }

    public String encode() {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(map.get(c).code);
        }
        return sb.toString();
    }

    /**
     * 從根結點出發，尋找數字對應的字符
     * 數字是 0 向左走
     * 數字是 1 向右走
     * 如果沒有走到頭，每走一步數字的索引 i++。走道頭就可以找到解碼字符，再將 node 重置維根節點
     **/
    public String decode(String str) {
        char[] chars = str.toCharArray();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        Node node = root;
        while (i < chars.length) {
            if (!node.isLeaf()) { // 非葉子
                if (chars[i] == '0') {
                    // 向左走
                    node = node.left;
                } else if (chars[i] == '1') {
                    // 向右走
                    node = node.right;
                }
                i++;
            }
            if (node.isLeaf()) {
                // 葉子
                sb.append(node.ch);
                node = root;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        HuffmanTree tree = new HuffmanTree("abbccccccc");
        String encoded = tree.encode();
        System.out.println(encoded);
        System.out.println(tree.decode(encoded));
    }

}
