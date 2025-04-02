package com.datastructure.demo;

import com.datastructure.demo.linkedList.DoublyLinkedListSentinel;
import org.junit.Test;

public class TestDoublyLinkedListSentinel {
    @Test
    public void test1(){
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();
        list.addFist(3);
        list.addFist(2);
        list.addLast(4);
        list.insert(2, 8);
        list.insert(2, 9);
        for(Integer i : list){
            System.out.println(i);
        }

        System.out.println("=============");
        list.removeFirst();
        for(Integer i : list){
            System.out.println(i);
        }

        System.out.println("=============");
        list.removeLast();
        for(Integer i : list){
            System.out.println(i);
        }

        System.out.println("=============");
        list.remove(2);
        for(Integer i : list){
            System.out.println(i);
        }
    }
}
