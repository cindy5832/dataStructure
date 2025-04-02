package com.datastructure.demo.leetcode;

import java.util.*;

public class LeetCode355 {
    class Tweet {
        int id;
        int time;
        Tweet next;

        public Tweet(int id, int time, Tweet next) {
            this.id = id;
            this.time = time;
            this.next = next;
        }

        public int getId() {
            return id;
        }

        public int getTime() {
            return time;
        }
    }

    class User {
        int id;

        public User(int id) {
            this.id = id;
        }

        Set<Integer> followers = new HashSet<>();
        Tweet head = new Tweet(-1, -1, null);
    }

    private final Map<Integer, User> userMap = new HashMap<>();
    private static int time;

    // 發布文章
    public void postTweet(int userId, int tweetId) {
        User user = userMap.computeIfAbsent(userId, User::new);
        user.head.next = new Tweet(tweetId, time++, user.head.next);
    }

    // 新增關注
    public void follow(int followerId, int followeeId) {
        User user = userMap.computeIfAbsent(followerId, User::new);
        User follower = userMap.computeIfAbsent(followeeId, User::new);
        user.followers.add(follower.id);
    }

    // 取消關注
    public void unfollow(int followerId, int followeeId) {
        User user = userMap.get(followerId);
        if (user != null) {
            user.followers.remove(followeeId);
        }
    }

    // 獲取最新篇文章 (包含自己已知關注者)
    public List<Integer> getNewsFeed(int userId) {
        User user = userMap.get(userId);
        if (user == null) {
            return List.of();
        }
        PriorityQueue<Tweet> queue = new PriorityQueue<>(Comparator.comparingInt(Tweet::getTime).reversed());
        if (user.head.next != null) {
            queue.offer(user.head.next);
        }
        for (Integer id : user.followers) {
            User follower = userMap.get(id);
            if (follower != null) {
                queue.offer(follower.head.next);
            }
        }
        List<Integer> result = new ArrayList<>();
        int count = 0;
        while (!queue.isEmpty() && count < 10) {
            Tweet max = queue.poll();
            result.add(max.id);
            if (max.next != null) {
                queue.offer(max.next);
            }
            count++;
        }
        return result;
    }
}
