package com.datastructure.demo.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// LeetCode 435 無重疊區間 - 活動選擇問題
public class ActivitySelectionProblem {
    static class Activity {
        int index;
        int start;
        int finish;

        public Activity(int index, int start, int finish) {
            this.index = index;
            this.start = start;
            this.finish = finish;
        }

        int getFinish() {
            return finish;
        }

        @Override
        public String toString() {
            return "Activity{" +
                    "index=" + index +
                    ", start=" + start +
                    ", finish=" + finish +
                    '}';
        }
    }

    public static void main(String[] args) {
//        Activity[] activities = new Activity[]{
//                new Activity(1, 2, 4),
//                new Activity(2, 3, 5),
//                new Activity(0, 1, 3),
//        };
//        Arrays.sort(activities, Comparator.comparing(Activity::getFinish));
//        System.out.println(Arrays.toString(activities));
//        select(activities, activities.length);
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        int[][] intervals1 = {{1, 2}, {1,2}, {1,2}};
        int a = eraseOverlapIntervals(intervals);
        int b = eraseOverlapIntervals(intervals1);
        System.out.println(a);
        System.out.println(b);
    }

    private static void select(Activity[] activities, int n) {
        List<Activity> result = new ArrayList<>();
        Activity prev = activities[0];
        result.add(prev); // 上次被選中的活動
        for (int i = 1; i < n; i++) {
            Activity curr = activities[i]; // 正在處理的活動
            if (curr.start >= prev.finish) {
                result.add(curr);
                prev = curr;
            }
        }
        for (Activity activity : result) {
            System.out.println(activity);
        }
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        int count = 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int[] prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];
            if(curr[0] < prev[1]) {
                count++;
            }else {
                prev = curr;
            }
        }
        return count;
    }

}
