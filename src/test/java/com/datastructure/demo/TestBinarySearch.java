package com.datastructure.demo;

import com.datastructure.demo.binarySearch.BinarySearch;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class TestBinarySearch {
    @Test
    public void test1() {
        int[] a = {7, 13, 21, 30, 38, 44, 52, 53};
        Assertions.assertEquals(0, BinarySearch.binarySearchBasic(a, 7));
        Assertions.assertEquals(1, BinarySearch.binarySearchBasic(a, 13));
        Assertions.assertEquals(2, BinarySearch.binarySearchBasic(a, 21));
        Assertions.assertEquals(3, BinarySearch.binarySearchBasic(a, 30));
        Assertions.assertEquals(4, BinarySearch.binarySearchBasic(a, 38));
        Assertions.assertEquals(5, BinarySearch.binarySearchBasic(a, 44));
        Assertions.assertEquals(6, BinarySearch.binarySearchBasic(a, 52));
        Assertions.assertEquals(7, BinarySearch.binarySearchBasic(a, 53));
    }

    @Test
    @DisplayName("binarySearchBasic 沒找到")
    public void test2() {
        int[] a = {7, 13, 21, 30, 38, 44, 52, 53};
        Assertions.assertEquals(-1, BinarySearch.binarySearchBasic(a, 0));
        Assertions.assertEquals(-1, BinarySearch.binarySearchBasic(a, 15));
        Assertions.assertEquals(-1, BinarySearch.binarySearchBasic(a, 60));
    }

    @Test
    @DisplayName("binarySearchAlternative 找到")
    public void test3() {
        int[] a = {7, 13, 21, 30, 38, 44, 52, 53};
        Assertions.assertEquals(0, BinarySearch.binarySearchAlternative(a, 7));
        Assertions.assertEquals(1, BinarySearch.binarySearchAlternative(a, 13));
        Assertions.assertEquals(2, BinarySearch.binarySearchAlternative(a, 21));
        Assertions.assertEquals(3, BinarySearch.binarySearchAlternative(a, 30));
        Assertions.assertEquals(4, BinarySearch.binarySearchAlternative(a, 38));
        Assertions.assertEquals(5, BinarySearch.binarySearchAlternative(a, 44));
        Assertions.assertEquals(6, BinarySearch.binarySearchAlternative(a, 52));
        Assertions.assertEquals(7, BinarySearch.binarySearchAlternative(a, 53));
    }

    @Test
    @DisplayName("binarySearchAlternative 沒找到")
    public void test4() {
        int[] a = {7, 13, 21, 30, 38, 44, 52, 53};
        Assertions.assertEquals(-1, BinarySearch.binarySearchAlternative(a, 0));
        Assertions.assertEquals(-1, BinarySearch.binarySearchAlternative(a, 15));
        Assertions.assertEquals(-1, BinarySearch.binarySearchAlternative(a, 60));
    }

    @Test
    @DisplayName("binarySearchLeftmost1")
    public void test5() {
        int[] a = {1, 2, 4, 4, 4, 5, 6, 7};
        Assertions.assertEquals(0, BinarySearch.binarySearchLeftmost1(a, 1));
        Assertions.assertEquals(1, BinarySearch.binarySearchLeftmost1(a, 2));
        Assertions.assertEquals(2, BinarySearch.binarySearchLeftmost1(a, 4));
        Assertions.assertEquals(5, BinarySearch.binarySearchLeftmost1(a, 5));
        Assertions.assertEquals(6, BinarySearch.binarySearchLeftmost1(a, 6));
        Assertions.assertEquals(7, BinarySearch.binarySearchLeftmost1(a, 7));

        Assertions.assertEquals(-1, BinarySearch.binarySearchLeftmost1(a, 0));
        Assertions.assertEquals(-1, BinarySearch.binarySearchLeftmost1(a, 3));
        Assertions.assertEquals(-1, BinarySearch.binarySearchLeftmost1(a, 8));
    }
}
