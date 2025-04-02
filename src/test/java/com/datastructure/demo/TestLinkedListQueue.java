package com.datastructure.demo;

import com.datastructure.demo.queue.LinkedListQueue;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class TestLinkedListQueue {
    @Test
    public void test1() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        Assertions.assertIterableEquals(List.of(1, 2, 3, 4, 5), queue);
    }

    @Test
    public void test2() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        Assertions.assertNull(queue.peek());
        queue.offer(1);
        Assertions.assertEquals(1, queue.peek());
        queue.offer(2);
        Assertions.assertEquals(1, queue.peek());

    }

    @Test
    public void test3() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);

        Assertions.assertEquals(1, queue.poll());
        Assertions.assertEquals(2, queue.poll());
        Assertions.assertEquals(3, queue.poll());
        Assertions.assertEquals(4, queue.poll());
        Assertions.assertEquals(5, queue.poll());
        Assertions.assertNull(queue.poll());
    }

    @Test
    public void test4() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        Assertions.assertFalse(queue.offer(4));
        Assertions.assertFalse(queue.offer(5));

        Assertions.assertIterableEquals(List.of(1, 2, 3), queue);

    }
}
