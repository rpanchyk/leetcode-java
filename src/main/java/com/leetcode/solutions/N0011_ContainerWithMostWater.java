package com.leetcode.solutions;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class N0011_ContainerWithMostWater {

    public static void main(String[] args) {
        N0011_ContainerWithMostWater p = new N0011_ContainerWithMostWater();

        System.out.println(p.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(p.maxArea(toArray()));
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

    private static int[] toArray() {
        Path path;
        try {
            path = Path.of(ClassLoader.getSystemResource("N0011.txt").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            String[] split = line.split(",");
            int[] result = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                result[i] = Integer.parseInt(split[i].trim());
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
