package com.datastructure.demo.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCode77 {
    List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(1, n, k, new LinkedList<>(), result);
        return result;
    }

    private void dfs(int start, int n, int k, LinkedList<Integer> stack, List<List<Integer>> result) {
        if(stack.size() == k) {
            result.add(new ArrayList<>(stack));
            return;
        }
        for(int i = start; i <= n; i++) {
            stack.push(i);
            dfs(i + 1, n, k, stack, result);
            stack.pop();

        }
    }

    public static void main(String[] args) {
        LeetCode77 leetcode77 = new LeetCode77();
        List<List<Integer>> lists = leetcode77.combine(4, 2);
        for(List<Integer> list : lists){
            System.out.println(list);
        }
    }
    /**
     * n 數字範圍 1~4
     * k 數字個數
     * 不考慮順序
     *      12
     *      13
     *      14
     *      23
     *      24
     *      34
     **/
}
