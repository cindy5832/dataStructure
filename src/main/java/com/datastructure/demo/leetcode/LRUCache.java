package com.datastructure.demo.leetcode;

import java.util.HashMap;

// LeetCode146
public class LRUCache {
    class Node {
        Node prev;
        Node next;
        int key;
        int value;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    class DoublyLinkedList {
        Node head;
        Node tail;

        public DoublyLinkedList() {
            head = tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        // 添加頭部
        public void addFirst(Node newFirst) {
            Node oldFirst = head.next;
            newFirst.prev = head;
            newFirst.next = oldFirst;
            head.next = newFirst;
            oldFirst.prev = newFirst;
        }

        // 已知節點刪除
        public void remove(Node node) {
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
        }

        // 尾部刪除
        public Node removeLast() {
            Node last = tail.prev;
            remove(last);
            return last;
        }

    }

    private HashMap<Integer, Node> map = new HashMap<>();
    private DoublyLinkedList list = new DoublyLinkedList();
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        list.remove(node);
        list.addFirst(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) { // 更新
            Node node = map.get(key);
            node.value = value;
            list.remove(node);
            list.addFirst(node);
        } else { // 新增
            Node node = new Node(key, value);
            map.put(key, node);
            list.addFirst(node);
            if (map.size() > capacity) {
                Node removed = list.removeLast();
                map.remove(removed.key);
            }
        }
    }

    public static void main(String[] args) {

    }
}
