package com.datastructure.demo.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// LeetCode42
public class TrappingRainWater {
    public static void main(String[] args) {
        TrappingRainWater t = new TrappingRainWater();
        System.out.println(t.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    int trap1(int[] heights) {
        LinkedList<Data> stack = new LinkedList<>();
        int sum = 0;
        for (int i = 0; i < heights.length; i++) {
            Data right = new Data(heights[i], i);
            while (!stack.isEmpty() && stack.peek().height < right.height) {
                Data pop = stack.pop();
                Data left = stack.peek();
                if (left != null) {
                    // 計算水的容量
                    int width = right.i - left.i - 1;
                    int height = Math.min(left.height, right.height) - pop.height;
                    sum += width * height;
                }
            }
            stack.push(right);
            System.out.println(stack);
        }
        return sum;
    }

    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[left];
        int rightMax = height[right];
        int sum = 0;
        while (left < right) {
            if (leftMax < rightMax) {
                left++;
                leftMax = Math.max(leftMax, height[left]);
                sum += leftMax - height[left];
            }else{
                right--;
                rightMax = Math.max(rightMax, height[right]);
                sum += rightMax - height[right];
            }
        }
        return sum;
    }

    class Data {
        int height;
        int i; // index

        public Data(int height, int i) {
            this.height = height;
            this.i = i;
        }

        @Override
        public String toString() {
            return String.valueOf(height);
        }
    }
}
