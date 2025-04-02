package com.datastructure.demo.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PermuteLeetCode46 {
    List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, new boolean[nums.length], new LinkedList<>(), result);
        return result;
    }

    void dfs(int[] nums, boolean[] visit, LinkedList<Integer> stack, List<List<Integer>> result) {
        if (stack.size() == nums.length) {
            result.add(new ArrayList<>(stack));
            return;
        }
        // 遍歷 nums 數組，發現未被使用的數字，則將其標記為使用，並加入stack
        for (int i = 0; i < nums.length; i++) {
            if(i > 0 && nums[i] == nums[i-1]  && !visit[i-1]){
                continue;
            }
            if (!visit[i]) {
                stack.push(nums[i]);
                visit[i] = true;
                dfs(nums, visit, stack, result);
                visit[i] = false;
                stack.pop();
            }
        }

    }



    public static void main(String[] args) {
        List<List<Integer>> permute = new PermuteLeetCode46().permute(new int[]{1, 2, 3});
        System.out.println("===========================");
        for (List<Integer> list : permute) {
            System.out.println(list);
        }
    }
}
