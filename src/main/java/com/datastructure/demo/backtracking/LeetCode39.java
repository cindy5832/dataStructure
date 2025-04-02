package com.datastructure.demo.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCode39 {
    List<List<Integer>> combination(int[] candidates, int target){
        List<List<Integer>> result = new ArrayList<>();
        dfs(0, candidates, target, new LinkedList<>(), result);
        return result;
    }
    private void dfs(int start, int[] candidates, int target, LinkedList<Integer> stack, List<List<Integer>> result){
        if(target == 0){
            result.add(new ArrayList<>(stack));
            return;
        }
        for(int i = start; i < candidates.length; i++){
            int candidate = candidates[i];

            if(target < candidate){
                continue;
            }
            stack.push(candidate);
            dfs(i, candidates, target-candidate, stack, result);
            stack.pop();
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new LeetCode39().combination(new int[]{2,3,6,7}, 7);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
}
