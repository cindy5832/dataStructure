package com.datastructure.demo;

import com.datastructure.demo.queue.ArrayQueue1;
import com.datastructure.demo.queue.LinkedListQueue;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class TestArrayQueue {
    @Test
    public void test1() {
        ArrayQueue1<Integer> queue = new ArrayQueue1<>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        Assertions.assertIterableEquals(List.of(1, 2, 3), queue);
    }

    @Test
    public void test2() {
        ArrayQueue1<Integer> queue = new ArrayQueue1<>(3);
        Assertions.assertNull(queue.peek());
        queue.offer(1);
        Assertions.assertEquals(1, queue.peek());
        queue.offer(2);
        Assertions.assertEquals(1, queue.peek());

    }


    @Test
    public void test4() {
        ArrayQueue1<Integer> queue = new ArrayQueue1<>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        Assertions.assertFalse(queue.offer(4));
        Assertions.assertFalse(queue.offer(5));

        Assertions.assertIterableEquals(List.of(1, 2, 3), queue);

    }
}
