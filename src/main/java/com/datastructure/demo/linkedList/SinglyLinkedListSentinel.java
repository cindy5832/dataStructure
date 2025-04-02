package com.datastructure.demo.linkedList;

import java.util.Iterator;
import java.util.function.Consumer;

// 單向鏈表 (帶哨兵)
public class SinglyLinkedListSentinel implements Iterable<Integer> {
    private Node head = new Node(0, null); // 頭指針指向哨兵 value不重要可以任意值

    @Override // 遍歷3
    public Iterator<Integer> iterator() {
        // 匿名類部類
        return new Iterator<Integer>() {
            Node p = head.next;

            @Override
            public boolean hasNext() { // 是否有下一個元素
                return p != null;
            }

            @Override
            public Integer next() { // 返回當前值，並指向下一個元素
                int v = p.value;
                p = p.next;
                return v;
            }
        };
    }

    // 節點類
    // 當類別相對獨立，未用到外部變量時可以使用
    private static class Node {
        int value; // 值
        Node next; // 下個節點指針

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * @return void
     * @description: 向鏈表頭部添加
     * @param: [value] 待添加的值
     **/
    public void addFirst(int value) {
        insert(0, value);
    }

    // 遍歷1
    public void loop1(Consumer<Integer> consumer) {
        Node pointer = head.next;
        while (pointer != null) {
            consumer.accept(pointer.value);
            pointer = pointer.next;
        }
    }

    // 遍歷2
    public void loop2(Consumer<Integer> consumer) {
        for (Node p = head.next; p != null; p = p.next) {
            consumer.accept(p.value);
        }
    }

    private Node findLast() {
        Node p;
        for (p = head; p.next != null; p = p.next) {

        }
        return p;
    }

    /**
     * @return void
     * @description: 向鏈表尾部添加
     * @param: [value] 待添加值
     **/
    public void addLast(int value) {
        Node last = findLast();
        last.next = new Node(value, null);
    }

    /**
     * @return com.datastructure.demo.linkedList.SinglyLinkedList.Node
     * @description: 找到節點對象
     * @param: [index] 要找的索引位置
     **/
    private Node findNode(int index) {
        int i = -1; // 索引值
        for (Node p = head; p != null; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null; // 未找到
    }

    /**
     * @return int
     * @description: 找到索引值
     * @param: [index] 待找尋的索引值
     **/
    public int get(int index) {
        Node node = findNode(index);
        if (node == null) {
            throw illegalIndex(index); // [%d]數字佔位符
        }
        return node.value;
    }

    private IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException(String.format("index [%d] is illegal%n", index));
    }

    /**
     * @return void
     * @description: 向索引位置插入數值
     * @param: [index, value] index = 索引 value = 待插入值
     **/
    public void insert(int index, int value) throws IllegalArgumentException{
        Node prev = findNode(index - 1); // 找到上一個節點
        if (prev == null) {
            throw illegalIndex(index);
        }
        prev.next = new Node(value, prev.next);
    }

    /**
     * @return void 若不存在拋出index非法異常
     * @description: 刪除第一個
     * @param: []
     **/
    public void removeFirst() {
        remove(0);
    }

    public void remove(int index) {
        Node prev = findNode(index - 1); // 上一個節點
        if (prev == null) {
            throw illegalIndex(index);
        }
        Node removed = prev.next; // 被刪除的節點
        if (removed == null) {
            throw illegalIndex(index);
        }
        prev.next = removed.next;

    }

//    public void test() {
//        int i = 0;
//        for (Node p = head; p != null; p = p.next, i++) {
//            System.out.println(p.value + " index=" + i);
//        }
//    }
}
