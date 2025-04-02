package com.datastructure.demo.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetCode40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(0, candidates, new boolean[candidates.length], target, new LinkedList<>(), result);
        return result;
    }

    private void dfs(int start, int[] candidates, boolean[] visited, int target, LinkedList<Integer> stack, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(stack));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            int candidate = candidates[i];

            if (target < candidate) {
                continue;
            }
            if(i > 0 && candidate == candidates[i-1] && !visited[i-1]) {
                continue;
            }
            visited[i] = true;
            stack.push(candidate);
            dfs(i + 1, candidates, visited, target - candidate, stack, result);
            stack.pop();
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        LeetCode40 leetcode40 = new LeetCode40();
        List<List<Integer>> list = leetcode40.combinationSum2(candidates, 8);
        list.forEach(System.out::println);
    }
}
