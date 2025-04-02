package com.datastructure.demo.leetcode;

import java.util.*;

// LeetCode239
public class MonotonicQueue {
    private LinkedList<Integer> dqueue = new LinkedList<>();

    public Integer peek() {
        return dqueue.peekFirst();
    }

    public void poll() {
        dqueue.pollFirst();
    }

    public void offer(Integer t) {
        while (!dqueue.isEmpty() && dqueue.peekLast() < t) {
            dqueue.pollLast();
        }
        dqueue.offerLast(t);
    }

    int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue queue = new MonotonicQueue();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k && queue.peek() == nums[i - k]) {
                queue.poll();
            }
            int num = nums[i];
            queue.offer(num);
            if (i >= k - 1) {
                System.out.println(queue.peek());
                list.add(queue.peek());
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] maxSlidingWindow1(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i >= k && deque.peek() == nums[i - k]) {
                deque.poll();
            }
            int num = nums[i];
            while (!deque.isEmpty() && deque.peekLast() < num) {
                deque.pollLast();
            }
            deque.addLast(num);
            if (i >= k - 1) {
                list.add(deque.peek());
            }
            System.out.println(deque);
            System.out.println("peek:" + deque.peek());
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    @Override
    public String toString() {
        return "MonotonicQueue{" +
                "dqueue=" + dqueue +
                '}';
    }

    public static void main(String[] args) {
        MonotonicQueue q = new MonotonicQueue();
//        for (int i : new int[]{1, 3, -1, -3, 5, 3, 6, 7}) {
//            q.offer(i);
//            System.out.println(q);
//        int[] result = q.maxSlidingWindow1(new int[]{1, 3, -1, -3, -4, 5, 3, 6, 7}, 3);
        int[] result = q.maxSlidingWindow1(new int[]{1,-1}, 1);
        System.out.println(Arrays.toString(result));
    }
}


