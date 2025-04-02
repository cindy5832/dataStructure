package com.datastructure.demo.linkedList;

import java.util.Iterator;

// 雙向環形哨兵鏈表
public class DoubleLinkedListSentinel implements Iterable<Integer> {
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = sentinel.next;

            @Override
            public boolean hasNext() {
                return p != sentinel;
            }

            @Override
            public Integer next() {
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    private static class Node {
        Node prev;
        int value;
        Node next;

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private Node sentinel = new Node(null, -1, null);

    public DoubleLinkedListSentinel() {
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /**
     * @return void
     * @description: 從第一個添加
     * @param: [value] 待添加的值
     **/
    public void addFirst(int value) {
        Node a = sentinel;
        Node b = sentinel.next;
        Node added = new Node(a, value, b);
        a.next = added;
        b.prev = added;

    }

    /**
     * @return void
     * @description: 從後面添加
     * @param: [value] 待添加的值
     **/
    public void addLast(int value) {
        Node a = sentinel;
        Node b = sentinel.prev;
        Node added = new Node(b, value, a);
        b.next = added;
        a.prev = added;
    }

    /**
     * @return void
     * @description: 刪除第一個
     * @param: []
     **/
    public void removeFirst() {
        Node last = sentinel.next;
        if (last == sentinel) {
            throw new IllegalArgumentException("illegal!!");
        }
        Node a = sentinel;
        Node b = last.next;
        a.next = b;
        b.prev = a;
    }

    /**
     * @return void
     * @description: 刪除最後一個
     * @param: []
     **/
    public void removeLast() {
        Node last = sentinel.prev;
        if (last == sentinel) {
            throw new IllegalArgumentException("illegal!!");
        }
        Node a = last.prev;
        Node b = sentinel;
        a.next = b;
        b.prev = a;
    }

    /**
     * @return void
     * @description: 根據值刪除
     * @param: [value] 目標值
     **/
    public void removeByValue(int value) {
        Node removed = findByValue(value);
        if (removed == null){
            return; // 不用刪
        }
        Node a = removed.prev;
        Node b = removed.next;
        a.next = b;
        b.prev = a;
    }

    private Node findByValue(int value) {
        Node p = sentinel.next;
        while (p != sentinel) {
            if (p.value == value) {
                return p;
            }
            p = p.next;
        }
        return null;
    }


}
