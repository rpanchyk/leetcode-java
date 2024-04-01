package com.leetcode.solutions;

import com.leetcode.utils.FileUtils;

import static com.leetcode.utils.AssertUtils.assertThat;

public class N0011_ContainerWithMostWater {

    public static void main(String[] args) {
        assertThat(new N0011_ContainerWithMostWater().maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}), 49);
        assertThat(new N0011_ContainerWithMostWater().maxArea(toArrayFromFile("N0011.txt")), 721737540);
    }

    public int maxArea(int[] height) {
        int result = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int hLeft = height[left];
            int hRight = height[right];

            int h = Math.min(hLeft, hRight);
            int w = right - left;
            result = Math.max(result, h * w);

            if (hLeft < hRight) {
                left++;
            } else {
                right--;
            }
        }

        return result;
    }

    public int maxArea1(int[] height) {
        int result = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int h = Math.min(height[i], height[j]);
                int w = j - i;
                result = Math.max(result, h * w);
            }
        }
        return result;
    }

    private static int[] toArrayFromFile(String file) {
        String content = FileUtils.read(file);
        String[] split = content.split(",");
        int[] result = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            result[i] = Integer.parseInt(split[i].trim());
        }
        return result;
    }
}
