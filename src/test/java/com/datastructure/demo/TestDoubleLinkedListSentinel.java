package com.datastructure.demo;

import com.datastructure.demo.linkedList.DoubleLinkedListSentinel;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class TestDoubleLinkedListSentinel {

    private DoubleLinkedListSentinel getList(){
        DoubleLinkedListSentinel list = new DoubleLinkedListSentinel();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        return list;
    }

    @Test
    public void test1(){
        DoubleLinkedListSentinel list = new DoubleLinkedListSentinel();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);
        Assertions.assertIterableEquals(List.of(5,4,3,2,1),list);

        list.addLast(6);
        list.addLast(7);
        Assertions.assertIterableEquals(List.of(5,4,3,2,1,6,7), list);
    }

    @Test
    public void test2(){
        DoubleLinkedListSentinel list = new DoubleLinkedListSentinel();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

        list.removeFirst();
        Assertions.assertIterableEquals(List.of(2,1), list);
        list.removeFirst();
        Assertions.assertIterableEquals(List.of(1), list);
        list.removeFirst();
        Assertions.assertIterableEquals(List.of(), list);
        Assertions.assertThrows(IllegalArgumentException.class, list::removeFirst);
    }


    @Test
    public void test3(){
        DoubleLinkedListSentinel list = new DoubleLinkedListSentinel();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

        list.removeLast();
        Assertions.assertIterableEquals(List.of(3,2), list);
        list.removeLast();
        Assertions.assertIterableEquals(List.of(3), list);
        list.removeLast();
        Assertions.assertIterableEquals(List.of(), list);
        Assertions.assertThrows(IllegalArgumentException.class, list::removeFirst);
    }

    @Test
    public void test4(){
        DoubleLinkedListSentinel list = getList();
        Assertions.assertIterableEquals(List.of(1,2,3), list);
    }

    @Test
    public void removeByValue(){
        DoubleLinkedListSentinel list = getList();
        list.removeByValue(2);
        Assertions.assertIterableEquals(List.of(1,3), list);
    }
}
