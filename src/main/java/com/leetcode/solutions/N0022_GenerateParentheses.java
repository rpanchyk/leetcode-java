package com.leetcode.solutions;

import java.util.*;

import static com.leetcode.utils.AssertUtils.assertThat;

public class N0022_GenerateParentheses {

    public static void main(String[] args) {
        assertThat(new N0022_GenerateParentheses().generateParenthesis(1),
            List.of("()")
        );
        assertThat(new N0022_GenerateParentheses().generateParenthesis(2),
            List.of("()()", "(())")
        );
        assertThat(new N0022_GenerateParentheses().generateParenthesis(3),
            List.of("()()()", "(()())", "()(())", "((()))", "(())()")
        );
        assertThat(new N0022_GenerateParentheses().generateParenthesis(4),
            List.of("()()()()", "(()())()", "(()(()))", "()()(())", "(((())))", "(())()()", "(())(())", "()((()))", "()(())()", "()(()())", "(()()())", "((()()))", "((()))()", "((())())")
        );
        assertThat(new N0022_GenerateParentheses().generateParenthesis(5),
            List.of("()(())()()", "(((()))())", "((()(())))", "((()))(())", "()()(()())", "(()(()))()", "()(()()())", "(()(()()))", "()((()))()", "()((()()))", "(((()())))", "(()()()())", "()()()()()", "((()())())", "(())()()()", "()(()())()", "(()(())())", "()(()(()))", "(((())))()", "(((())()))", "(()())(())", "()(((())))", "(())(())()", "(()((())))", "(())((()))", "()(())(())", "()()(())()", "()()((()))", "((()))()()", "((())()())", "((((()))))", "((()()()))", "(()()())()", "(()()(()))", "()((())())", "(())()(())", "((()()))()", "((())())()", "((())(()))", "()()()(())", "(())(()())", "(()())()()")
        );
    }

    private Map<Integer, List<String>> cache = new HashMap<>();

    public List<String> generateParenthesis(int n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        Set<String> result = new HashSet<>();

        char[] arr = ("(".repeat(n) + ")".repeat(n)).toCharArray();

        // Algo:
        // symmetric
        // forward: side -> center 1/4
        // reverse: center -> side 1/4

        // asymmetric (while leftN > 0)
        // forward: () + self(leftN)
        // reverse: self(leftN) + ()

        // reduce envelope pair (while leftN > 0)
        // ( + self(leftN) + )

        // Example:
        // (((())))
        // ((()()))
        // (())(())
        //
        // (()()())
        //
        // ()(())()
        // ()()()()
        //
        //---
        //
        // () ((())) - ((())) ()
        // () (()()) - (()()) ()
        //
        // ()() (()) - (()) ()()
        //
        // -()()() ()
        //
        //---
        //
        // ( (()()) )
        // ( () (()) ) - ( (()) () )

        // def
        result.add(String.valueOf(arr));

        // s f
        char[] chars = Arrays.copyOf(arr, arr.length);
        for (int i = 1; i < n; i += 2) {
            chars[i] = flip(chars[i]);
            chars[arr.length - 1 - i] = flip(chars[arr.length - 1 - i]);

            if (isValid(chars)) {
                result.add(String.valueOf(chars));
            }
        }

        // s r
        chars = Arrays.copyOf(arr, arr.length);
        for (int i = n; i > 0; i -= 2) {
            chars[i] = flip(chars[i]);
            chars[arr.length - 1 - i] = flip(chars[arr.length - 1 - i]);

            if (isValid(chars)) {
                result.add(String.valueOf(chars));
            }
        }

        // as
        int leftN = n - 1;
        while (leftN > 0) {
            List<String> lefts = generateParenthesis(leftN);
            List<String> rights = generateParenthesis(n - leftN);

            String opened = "(".repeat(n - leftN);
            String closed = ")".repeat(n - leftN);

            for (String left : lefts) {
                for (String right : rights) {
                    String e1 = right + left;
                    if (isValid(e1.toCharArray())) {
                        result.add(e1);
                    }
                    String e2 = left + right;
                    if (isValid(e2.toCharArray())) {
                        result.add(e2);
                    }
                }

                String e3 = opened + left + closed;
                if (isValid(e3.toCharArray())) {
                    result.add(e3);
                }
            }
            leftN--;
        }

        List<String> list = result.stream().toList();
        if (!cache.containsKey(n)) {
            cache.put(n, list);
        }
        return list;
    }

    private boolean isValid(char[] arr) {
        int count = 0;
        for (char c : arr) {
            count = (c == '(') ? count + 1 : count - 1;
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    private char flip(char c) {
        return c == '(' ? ')' : '(';
    }
}
