package com.leetcode.solutions;

import java.util.Arrays;

import static com.leetcode.utils.AssertUtils.assertThat;

public class N0016_3SumClosest {

    public static void main(String[] args) {
        assertThat(new N0016_3SumClosest().threeSumClosest(new int[]{-1, 2, 1, -4}, 1), 2);
        assertThat(new N0016_3SumClosest().threeSumClosest(new int[]{0, 0, 0}, 1), 0);
        assertThat(new N0016_3SumClosest().threeSumClosest(new int[]{4, 0, 5, -5, 3, 3, 0, -4, -5}, -2), -2);
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int diff = Math.abs(sum - target);

                if (diff < Math.abs(result - target)) {
                    result = sum;
                }

                if (diff == 0) {
                    return result;
                } else {
                    if (sum > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return result;
    }
}
