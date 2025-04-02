package com.datastructure.demo.linkedList;

import java.util.Iterator;

// 雙向鍊表 (帶哨兵)
public class DoublyLinkedListSentinel implements Iterable<Integer> {
    static class Node {
        Node prev; // 上一個節點指針
        int value; // 值
        Node next; // 下一個節點指針

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private Node head; // 頭部哨兵
    private Node tail; // 尾部哨兵

    public DoublyLinkedListSentinel() {
        head = new Node(null, 0, null); // value值可以任意值
        tail = new Node(null, 1, null);
        head.next = tail;
        tail.prev = head;
    }

    private Node findNode(int index) {
        int i = -1;
        for (Node p = head; p != tail; i++, p = p.next) {
            if (i == index) {
                return p;
            }
        }
        return null;
    }

    public void insert(int index, int value) {
        Node prev = findNode(index - 1);
        if (prev == null) {
            throw illegalIndex(index);
        }
        Node next = prev.next;
        Node inserted = new Node(prev, value, next);
        prev.next = inserted;
        next.prev = inserted;
    }

    public void remove(int index){
        Node prev = findNode(index - 1);
        if (prev == null){
            throw illegalIndex(index);
        }
        Node removed = prev.next;
        if(removed == tail){
            throw illegalIndex(index);
        }
        Node next = removed.next;
        prev.next = next;
        next.prev = prev;
    }

    public void addFist(int value) {
        insert(0, value);
    }

    public void removeFirst() {
        remove(0);
    }

    public void addLast(int value) {
        Node last = tail.prev;
        Node added = new Node(last, value, tail);
        last.next = added;
        tail.prev = added;
    }

    public void removeLast() {
        Node removed = tail.prev;
        if(removed == head){
            throw illegalIndex(0);
        }
        Node prev = removed.prev;
        prev.next = tail;
        tail.prev = prev;


    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = head.next;
            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public Integer next() {
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    private IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException(String.format("index [%d] is illegal%n", index));
    }
}
