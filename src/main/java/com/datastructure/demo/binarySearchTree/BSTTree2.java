package com.datastructure.demo.binarySearchTree;

public class BSTTree2<T extends Comparable<T>, V> {
    static class BSTNode<T, V> {
        T key;
        V value;
        BSTNode<T, V> left;
        BSTNode<T, V> right;

        public BSTNode(T key) {
            this.key = key;
        }

        public BSTNode(T key, V value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(T key, V value, BSTNode<T, V> left, BSTNode<T, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }


    BSTNode<T, V> root;

    // 查詢key對應的value
    public V get(T key) {
        BSTNode<T, V> p = root;
        while (p != null) {
            int result = key.compareTo(p.key); // 返回-1 表示Key < p.key; 0 key = p.key; 1 key > p.key
            if (result < 0) {
                p = p.left;
            } else if (result > 0) {
                p = p.right;
            } else {
                return p.value;
            }
        }
        return null;
    }

    // 查詢最小關鍵字對應的值
    public V min() {
        return null;
    }


    // 查詢最大關鍵字對應的值
    public V max() {
        return null;
    }

    // 存key & value
    public void put(T key, V value) {

    }

    // 查關鍵字的前驅值
    public V successor(T key) {
        return null;
    }

    // 查詢關鍵字的後繼值
    public V predecessor(T key) {
        return null;
    }

    // 根據關鍵字刪除 返回被刪除的對應值
    public V delete(T key) {
        return null;
    }
}
