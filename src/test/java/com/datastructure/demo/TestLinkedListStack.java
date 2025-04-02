package com.datastructure.demo;

import com.datastructure.demo.stack.LinkedListStack;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TestLinkedListStack {
    @Test
    public void pop(){
        LinkedListStack<Object> stack = new LinkedListStack<>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Assertions.assertEquals(3, stack.pop());
        Assertions.assertEquals(2, stack.pop());
        Assertions.assertEquals(1, stack.pop());
        Assertions.assertNull(stack.pop());

    }
}
