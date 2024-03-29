package com.leetcode.solutions;

public class N009_PalindromeNumber {

    public static void main(String[] args) {
        System.out.println(new N009_PalindromeNumber().isPalindrome(121));
        System.out.println(new N009_PalindromeNumber().isPalindrome(-121));
        System.out.println(new N009_PalindromeNumber().isPalindrome(10));
    }

    public boolean isPalindrome(int x) {
        final String leftToRight = String.valueOf(x);
        final String rightToLeft = new StringBuilder(leftToRight).reverse().toString();
        return leftToRight.equals(rightToLeft);
    }
}
