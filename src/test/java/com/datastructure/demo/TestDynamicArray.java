package com.datastructure.demo;

import com.datastructure.demo.linkedList.DynamicArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestDynamicArray {
    @Test
    public void test1() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);
        dynamicArray.addLast(5);
        dynamicArray.add(2, 5);

        dynamicArray.foreach(System.out::println);
    }

    @Test
    public void test2() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);
        for (Integer element : dynamicArray) {
            System.out.println(element);
        }
    }

    @Test
    public void test3() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);
        dynamicArray.stream().forEach(System.out::println);
    }

    @Test
    public void test4() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);
        dynamicArray.addLast(5);

        int remove = dynamicArray.remove(2);
//        System.out.println(remove);
//        System.out.println("--------------");
//        dynamicArray.stream().forEach(System.out::println);
        Assertions.assertEquals(3, remove);
        Assertions.assertIterableEquals(List.of(1, 2, 4, 5), dynamicArray); // 前提是必須實現Iterator 接口
    }

    @Test
    public void test5() {
        DynamicArray dynamicArray = new DynamicArray();
        for (int i = 0; i < 9; i++) {
            dynamicArray.addLast(i + 1);
        }
        Assertions.assertIterableEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), dynamicArray);
    }
}
