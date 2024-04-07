package com.leetcode.solutions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static com.leetcode.utils.AssertUtils.assertThat;

public class N0018_4Sum {

    public static void main(String[] args) {
        assertThat(new N0018_4Sum().fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0), List.of(
            List.of(-2, 0, 0, 2),
            List.of(-2, -1, 1, 2),
            List.of(-1, 0, 0, 1)
        ));
        assertThat(new N0018_4Sum().fourSum(new int[]{2, 2, 2, 2, 2}, 8), List.of(
            List.of(2, 2, 2, 2)
        ));
        assertThat(new N0018_4Sum().fourSum(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296), List.of(
            // empty
        ));
        assertThat(new N0018_4Sum().fourSum(new int[]{0, 0, 0, 1000000000, 1000000000, 1000000000, 1000000000}, 1000000000), List.of(
            List.of(0, 0, 0, 1000000000)
        ));
        assertThat(new N0018_4Sum().fourSum(new int[]{0, 0, 0, -1000000000, -1000000000, -1000000000, -1000000000}, -1000000000), List.of(
            List.of(-1000000000, 0, 0, 0)
        ));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                int left = j + 1;
                int right = nums.length - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j];
                    if (sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE) {
                        break;
                    }
                    sum += nums[left];
                    if (sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE) {
                        break;
                    }
                    sum += nums[right];
                    if (sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE) {
                        break;
                    }

                    if (sum == target) {
                        List<Integer> list = Stream.of(nums[i], nums[j], nums[left], nums[right]).sorted().toList();
                        result.add(list);
                        right--;
                        left++;
                    } else {
                        if (sum > target) {
                            right--;
                        } else {
                            left++;
                        }
                    }
                }
            }
        }
        return result.stream().toList();
    }
}
