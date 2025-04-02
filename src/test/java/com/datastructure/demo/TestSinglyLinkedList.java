package com.datastructure.demo;

import com.datastructure.demo.linkedList.SinglyLinkedList;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class TestSinglyLinkedList {
    @Test
    public void test1() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        list.loop1(System.out::println);
        list.loop2(System.out::println);
        for (Integer i : list) {
            System.out.println(i);
        }
    }

    @Test
    public void test3() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        Assertions.assertIterableEquals(List.of(1, 2, 3, 4), list);
        System.out.println(list.get(5));
    }

    @Test
    public void test4(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        list.insert(2,5);
        list.loop1(System.out::println);
    }

    @Test
    public void test5(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        list.removeFirst();
        list.loop1(System.out::println);
        list.loop3(value ->{
            System.out.println("before:" + value);
        }, value -> {
            System.out.println("after:" + value);
        });
    }

    @Test
    public void test6(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        list.remove(3);
        list.loop1(System.out::println);
    }
}
