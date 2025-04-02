package com.datastructure.demo.dynamicProgramming;

public class HouseRobberLeetCode198 {
    /**
     * price    0   1   2   3   4
     *          0   0   0   0   0
     * 0(2)     2   0   0   0   0
     * 1(7)     2   7   0   0   0
     * 2(9)     2   7   11  0   0
     * 3(3)     2   7   11  11  0
     * 4(1)     2   7   11  11  12
     **/
    public int rob(int[] house) {
        if(house.length == 1) return house[0];
        int[] dp = new int[house.length];
        dp[0] = house[0];
        dp[1] = Integer.max(house[0], house[1]);
        for(int i = 2;i < house.length;i++){
            dp[i] = Integer.max(dp[i-2] + house[i], dp[i-1]);
        }
        return dp[house.length-1];
    }

    public int rob1(int[] nums) {
        int len = nums.length;
        if(len == 1) return nums[0];
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Integer.max(nums[0], nums[1]);
        for(int i = 2;i < len;i++){
            dp[i] = Integer.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[len-1];
    }

    public static void main(String[] args) {
        HouseRobberLeetCode198 code = new HouseRobberLeetCode198();
        System.out.println(code.rob(new int[]{2,7,9,3,1}));
        System.out.println(code.rob(new int[]{2,1,1,2}));
    }
}
