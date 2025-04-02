package com.datastructure.demo.leetcode;

import java.net.UnknownServiceException;
import java.util.*;

public class Twitter1 {
    private int timeStamp = 0;
    private Map<Integer, User> userMap;

    public class Tweet {
        int id;
        int time;
        Tweet next;

        public Tweet(int id) {
            this.id = id;
            time = timeStamp++;
            next = null;
        }
    }

    public class User {
        int id;
        Set<Integer> followed;
        Tweet head;

        public User(int id) {
            this.id = id;
            followed = new HashSet<>();
            follow(id);
            head = null;
        }

        void follow(int id) {
            followed.add(id);
        }

        void unfollow(int id) {
            followed.remove(id);
        }

        void post(int id) {
            Tweet tweet = new Tweet(id);
            tweet.next = head;
            head = tweet;
        }
    }

    public Twitter1() {
        userMap = new HashMap<Integer, User>();
    }

    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new User(userId));
        }
        userMap.get(userId).post(tweetId);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();
        if (!userMap.containsKey(userId)) {
            return result;
        }
        Set<Integer> users = userMap.get(userId).followed;
        PriorityQueue<Tweet> q = new PriorityQueue<>(users.size(),
                (a, b) -> b.time - a.time);
        for (Integer user : users) {
            Tweet t = userMap.get(user).head;
            if (t != null) {
                q.add(t);
            }
        }
        int n = 0;
        while (!q.isEmpty() && n < 10) {
            Tweet t = q.poll();
            result.add(t.id);
            n++;
            if (t.next != null) {
                q.add(t.next);
            }
        }
        return result;
    }

    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
            userMap.put(followerId, new User(followerId));
        }
        if (!userMap.containsKey(followeeId)) {
            userMap.put(followeeId, new User(followeeId));
        }
        userMap.get(followerId).follow(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId) || followerId == followeeId) {
            return;
        }
        userMap.get(followerId).unfollow(followeeId);
    }
}
