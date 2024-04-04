package com.leetcode.utils;

import java.util.Arrays;
import java.util.Objects;

public class AssertUtils {

    static int counter = 0;

    public static void assertThat(Object actual, Object expected) {
        if (actual != null && actual.getClass().isArray()) {
            if (actual.getClass().getComponentType() == int.class) {
                actual = Arrays.toString((int[]) actual);
            }
        }
        if (expected != null && expected.getClass().isArray()) {
            if (expected.getClass().getComponentType() == int.class) {
                expected = Arrays.toString((int[]) expected);
            }
        }

        System.out.println(++counter + " => " + actual);
        if (!Objects.equals(actual, expected)) {
            throw new RuntimeException("Actual '" + actual + "', but expected '" + expected + "' :(");
        }
    }
}
