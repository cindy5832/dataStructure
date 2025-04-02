package com.datastructure.demo.leetcode;

import java.util.HashMap;

// leetcode460
public class LFUCache {
    class Node {
        Node prev;
        Node next;
        int key;
        int value;
        int freq = 1; // 使用頻率

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
        int size;

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
            size++;
        }

        // 已知節點刪除
        public void remove(Node node) {
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
            size--;
        }

        // 尾部刪除
        public Node removeLast() {
            Node last = tail.prev;
            remove(last);
            return last;
        }

        // 是否為空
        public boolean isEmpty(){
            return size == 0;
        }

    }

    private HashMap<Integer, Node> kvMap = new HashMap<>();
    private HashMap<Integer, DoublyLinkedList> freqMap = new HashMap<>();
    int capacity;
    private int minFreq = 1; // 最小頻率

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    /**
     * key 不存在 返回 -1
     * key 存在
     *  返回 value值
     *  增加節點的使用頻率，將其轉移到頻率+1的鍊表當中
     **/
    public int get(int key) {
        if(!kvMap.containsKey(key)) {
            return -1;
        }
        Node node = kvMap.get(key);
//        DoublyLinkedList list = freqMap.get(node.freq);
//        if(list == null){
//            list = new DoublyLinkedList();
//            freqMap.put(node.freq, list);
//        }
//        list.addFirst(node);
        DoublyLinkedList list = freqMap.get(node.freq);
        list.remove(node);
        if(list.isEmpty() && node.freq == minFreq) {
            minFreq++;
        }
        node.freq++;
        freqMap.computeIfAbsent(node.freq, k -> new DoublyLinkedList()).addFirst(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(kvMap.containsKey(key)) { // 更新
           Node node = kvMap.get(key);
            DoublyLinkedList list = freqMap.get(node.freq);
            list.remove(node);
           if(list.isEmpty() && node.freq == minFreq) {
               minFreq++;
           }
           node.freq++;
           freqMap.computeIfAbsent(node.freq, k -> new DoublyLinkedList()).addFirst(node);
           node.value = value;
        }else { // 新增
            if(kvMap.size() == capacity) {
                Node node = freqMap.get(minFreq).removeLast();
                kvMap.remove(node.key);
            }
            Node node = new Node(key, value);
            kvMap.put(key, node);
            freqMap.computeIfAbsent(1,k-> new DoublyLinkedList()).addFirst(node);
            minFreq = 1;
        }
    }
}
