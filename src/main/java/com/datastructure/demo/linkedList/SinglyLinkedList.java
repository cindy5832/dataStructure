package com.datastructure.demo.linkedList;

import java.util.Iterator;
import java.util.function.Consumer;

// 單向鏈表
public class SinglyLinkedList implements Iterable<Integer> {
    private Node head = null; // 頭指針

    @Override // 遍歷3
    public Iterator<Integer> iterator() {
        // 匿名類部類
        return new Iterator<Integer>() {
            Node p = head;

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
        // 1. 鏈表為空
        // head = new Node(value, null);
        // 2. 鏈表非空
        head = new Node(value, head);
    }

    // 遍歷1
    public void loop1(Consumer<Integer> consumer) {
        Node pointer = head;
        while (pointer != null) {
            consumer.accept(pointer.value);
            pointer = pointer.next;
        }
    }

    // 遍歷2
    public void loop2(Consumer<Integer> consumer) {
        for (Node p = head; p != null; p = p.next) {
            consumer.accept(p.value);
        }
    }

    public void loop3(Consumer<Integer> before,Consumer<Integer> after){
        recursion(head, before,after);
    }

    private void recursion(Node curr, Consumer<Integer> before, Consumer<Integer> after){ // 針對某個節點要進行的操作
        if (curr == null){return;}
        before.accept(curr.value);
        recursion(curr.next, before, after);
        after.accept(curr.value);
    }

    private Node findLast() {
        if (head == null) { // 空鏈表
            return null;
        }
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
        if (last == null) {
            addFirst(value);
            return;
        }
        last.next = new Node(value, null);
    }

    /**
     * @return com.datastructure.demo.linkedList.SinglyLinkedList.Node
     * @description: 找到節點對象
     * @param: [index] 要找的索引位置
     **/
    private Node findNode(int index) {
        int i = 0; // 索引值
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
    public void insert(int index, int value)  throws IllegalArgumentException{
        if (index == 0) {
            addFirst(value);
            return;
        }
        Node prev = findNode(index - 1); // 找到上一個節點
        if (prev == null) {
            throw illegalIndex(index);
        }
        prev.next = new Node(value, prev.next);
    }

    /**
     * @description: 刪除第一個
     * @param: []
     * @return void 若不存在拋出index非法異常
     **/
    public void removeFirst(){
        if(head == null){
            throw illegalIndex(0);
        }
        head = head.next;
    }

    public void remove(int index){
        if(index == 0){
            removeFirst();
        }
        Node prev = findNode(index - 1); // 上一個節點
        if(prev == null){
            throw illegalIndex(index);
        }
        Node removed = prev.next; // 被刪除的節點
        if(removed == null){
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
