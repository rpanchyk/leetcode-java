package com.leetcode.solutions;

import java.util.*;
import java.util.stream.IntStream;

public class N004_MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        System.out.println(new N004_MedianOfTwoSortedArrays().findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
        System.out.println(new N004_MedianOfTwoSortedArrays().findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        List<Integer> list = IntStream.concat(Arrays.stream(nums1), Arrays.stream(nums2))
                .boxed()
                .sorted()
                .toList();


        boolean isEven = (m + n) % 2 == 0;
        int index = isEven ? (m + n) / 2 - 1 : (m + n + 1) / 2 - 1;

        return isEven
                ? (list.get(index) + list.get(index + 1)) / 2.0
                : list.get(index);
    }
}
