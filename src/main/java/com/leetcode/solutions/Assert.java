package com.leetcode.solutions;

public class Assert {

    static int counter = 0;

    public static void assertThat(Object actual, Object expected) {
        System.out.println(++counter + " => " + actual);
        if (!actual.equals(expected)) {
            throw new RuntimeException("Actual '" + actual + "', but expected '" + expected + "' :(");
        }
    }
}
