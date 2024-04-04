package com.leetcode.solutions;

import java.util.Arrays;

import static com.leetcode.utils.AssertUtils.assertThat;

public class N0001_TwoSum {

    public static void main(String[] args) {
        assertThat(new N0001_TwoSum().twoSum(new int[]{2, 7, 11, 15}, 9), new int[]{0, 1});
        assertThat(new N0001_TwoSum().twoSum(new int[]{3, 2, 4}, 6), new int[]{1, 2});
        assertThat(new N0001_TwoSum().twoSum(new int[]{3, 3}, 6), new int[]{0, 1});
        assertThat(new N0001_TwoSum().twoSum(new int[]{-1, -2, -3, -4, -5}, -8), new int[]{2, 4});
    }

    public int[] twoSum(int[] nums, int target) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums);

        int[] r = null;
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int value = nums[left] + nums[right];

            if (value == target) {
                r = new int[]{nums[left], nums[right]};
                break;
            } else {
                if (value > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        int first = -1;
        int second = -1;
        if (r != null) {
            for (int i = 0; i < copy.length; i++) {
                if (first == -1 && r[0] == copy[i]) {
                    first = i;

                    if (second != -1) {
                        return first < second
                            ? new int[]{first, second}
                            : new int[]{second, first};
                    }
                    continue;
                }
                if (second == -1 && first != i && r[1] == copy[i]) {
                    second = i;
                    if (first != -1) {
                        return first < second
                            ? new int[]{first, second}
                            : new int[]{second, first};
                    }
                }
            }
        }

        return new int[]{};
    }

    public int[] twoSum1(int[] nums, int target) {
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
