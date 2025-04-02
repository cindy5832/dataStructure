package com.datastructure.demo.heap;

import java.util.PriorityQueue;

public class LeetCode295_2 {
    // 大頂堆
    private PriorityQueue<Integer> left = new PriorityQueue<>(
            (a, b) -> Integer.compare(b, a)
    );

    // 默認是小頂堆
    private PriorityQueue<Integer> right = new PriorityQueue<>();

    private void addNum(int num) {
        if (left.size() == right.size()) {
            right.offer(num);
            left.offer(right.poll());
        } else {
            left.offer(num);
            right.offer(left.poll());
        }
    }

    public double findMedian() {
        if (left.size() == right.size()) {
            return (left.peek() + right.peek()) / 2.0;
        } else {
            return right.peek();
        }
    }

    public static void main(String[] args) {

    }

}
