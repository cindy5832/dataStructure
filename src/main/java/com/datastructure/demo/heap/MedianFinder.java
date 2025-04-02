package com.datastructure.demo.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {

    private PriorityQueue<Integer> left = new PriorityQueue<>(
            (a, b) -> Integer.compare(b, a)
    );
    private PriorityQueue<Integer> right = new PriorityQueue<>();


    public MedianFinder() {

    }

    public void addNum(int num) {
        if (left.size() == right.size()) {
            left.offer(num);
            right.offer(left.poll());
        } else {
            right.offer(num);
            left.offer(right.poll());
        }
    }

    public double findMedian() {
        if (left.size() == right.size()) {
            return (left.peek() + right.peek()) / 2.0;
        } else {
            return left.peek();
        }
    }
}
