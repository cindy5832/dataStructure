package com.datastructure.demo.stack;

import com.datastructure.demo.queue.ArrayQueue3;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode225 {
    ArrayQueue3<Integer> queue3 = new ArrayQueue3<>(100);
    private int size = 0;

    public void push(int x) {
        queue3.offer(x);
        for (int i = 0; i < size; i++) {
            queue3.offer(queue3.poll());
        }
        size++;
    }

    public int pop() {
        size--;
        return queue3.poll();
    }

    public int top() {
        return queue3.peek();
    }

    public boolean empty() {
        return false;
    }

    public static void main(String[] args) {
        Queue<Integer> q1 = new LinkedList<>();
        q1.offer(1);
        q1.offer(2);
        q1.offer(3);
        System.out.println(q1);
        System.out.println(q1.size());
        System.out.println("=====");
        if(q1.isEmpty()) q1.offer(4);
        else{
            int size = q1.size();
            q1.offer(4);
            while (size > 0){
                q1.offer(q1.poll());
                size--;
            }
        }
        System.out.println(q1);

    }

}
