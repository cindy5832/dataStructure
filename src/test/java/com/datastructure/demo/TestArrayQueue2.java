package com.datastructure.demo;

import com.datastructure.demo.queue.ArrayQueue1;
import com.datastructure.demo.queue.ArrayQueue2;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class TestArrayQueue2 {

    @Test
    public void test1() {
        ArrayQueue2<Integer> queue = new ArrayQueue2<>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        Assertions.assertIterableEquals(List.of(1, 2, 3), queue);
    }

    @Test
    public void test2() {
        ArrayQueue2<Integer> queue = new ArrayQueue2<>(3);
        Assertions.assertNull(queue.peek());
        queue.offer(1);
        Assertions.assertEquals(1, queue.peek());
        queue.offer(2);
        Assertions.assertEquals(1, queue.peek());

    }


    @Test
    public void test4() {
        ArrayQueue2<Integer> queue = new ArrayQueue2<>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        Assertions.assertFalse(queue.offer(4));
        Assertions.assertFalse(queue.offer(5));

        Assertions.assertIterableEquals(List.of(1, 2, 3), queue);

    }
}
