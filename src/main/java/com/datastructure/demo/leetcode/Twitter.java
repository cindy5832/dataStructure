package com.datastructure.demo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Twitter {
    HashMap<Integer, List<Integer>> follow;
    HashMap<Integer, PriorityQueue<int[]>> post;
    int time;

    public Twitter() {
        follow = new HashMap<>();
        post = new HashMap<>();
        time = 0;
    }

    public void postTweet(int userId, int tweetId) {
        follow.computeIfAbsent(userId, k -> new ArrayList<>());
        if (post.containsKey(userId)) {
            post.get(userId).add(new int[]{tweetId, time++});
            return;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> b[1] - a[1]
        );
        pq.add(new int[]{tweetId, time++});
        post.put(userId, pq);
    }

    public List<Integer> getNewsFeed(int userId) {
        if (!follow.containsKey(userId)) {
            return new ArrayList<>();
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> b[1] - a[1]
        );
        List<Integer> list = follow.get(userId);
        list.add(userId);
        int last = -1;
        for (int i = 0; i < list.size(); i++) {
            if (!post.containsKey(list.get(i)) || list.get(i) == last) {
                continue;
            }
            last = list.get(i);
            PriorityQueue<int[]> pq2 = new PriorityQueue<>(post.get(list.get(i)));
            while (!pq2.isEmpty()) {
                pq.add(pq2.poll());
            }
        }
        list.remove(list.size() - 1);
        ArrayList<Integer> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            int[] tweet = pq.poll();
            int tweetId = tweet[0];
            result.add(tweetId);
            if (result.size() == 10) break;
        }
        return result;
    }

    public void follow(int followerId, int followeeId) {
        if (!follow.containsKey(followerId)) {
            follow.put(followerId, new ArrayList<>());
        }
        follow.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        List<Integer> list = follow.get(followerId);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == followeeId) {
                list.remove(i);
                break;
            }
        }
    }
}
