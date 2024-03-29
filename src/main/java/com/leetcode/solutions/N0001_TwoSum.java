package com.leetcode.solutions;

import java.util.Arrays;

public class N0001_TwoSum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new N0001_TwoSum().twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(new N0001_TwoSum().twoSum(new int[]{3, 2, 4}, 6)));
        System.out.println(Arrays.toString(new N0001_TwoSum().twoSum(new int[]{3, 3}, 6)));
    }

    public int[] twoSum(int[] nums, int target) {
        if (nums != null && nums.length > 1) {
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                        return new int[]{i, j};
                    }
                }
            }
        }
        return new int[]{};
    }
}
