package com.datastructure.demo.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCode216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(1, n, k, new LinkedList<>(), result);
        return result;
    }

    private void dfs(int start, int target, int k, LinkedList<Integer> stack, List<List<Integer>> result) {
        if (target == 0 && stack.size() == k) {
            result.add(new ArrayList<>(stack));
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (target < i) {
                continue;
            }
            if (stack.size() == k) {
                continue;
            }
            stack.push(i);
            dfs(i + 1, target - i, k, stack, result);
            stack.pop();

        }
    }

    public static void main(String[] args) {
        LeetCode216 leetCode216 = new LeetCode216();
        List<List<Integer>> lists = leetCode216.combinationSum3(3, 7);
        lists.forEach(System.out::println);
    }
}
