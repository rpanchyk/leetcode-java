package com.leetcode.solutions;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.leetcode.utils.AssertUtils.assertThat;

public class N0022_GenerateParentheses {

    public static void main(String[] args) {
        assertThat(new N0022_GenerateParentheses().generateParenthesis(1),
            List.of("()")
        );
        assertThat(new N0022_GenerateParentheses().generateParenthesis(2),
            List.of("(())", "()()")
        );
        assertThat(new N0022_GenerateParentheses().generateParenthesis(3),
            List.of("((()))", "()()()", "(()())", "()(())", "(())()")
        );
        assertThat(new N0022_GenerateParentheses().generateParenthesis(4),
            List.of("(((())))", "()(())()", "()()()()", "((()()))", "(())(())", "()((()))", "((()))()", "(()()())", "()(()())", "(()())()", "()()(())", "(()(()))", "(())()()", "((())())")
        );
        assertThat(new N0022_GenerateParentheses().generateParenthesis(5),
            List.of("((((()))))", "(((()())))", "(((())()))", "(((()))())", "(((())))()", "((()(())))", "((()()()))", "((()())())", "((()()))()", "((())(()))", "((())()())", "((())())()", "((()))(())", "((()))()()", "(()((())))", "(()(()()))", "(()(())())", "(()(()))()", "(()()(()))", "(()()()())", "(()()())()", "(()())(())", "(()())()()", "(())((()))", "(())(()())", "(())(())()", "(())()(())", "(())()()()", "()(((())))", "()((()()))", "()((())())", "()((()))()", "()(()(()))", "()(()()())", "()(()())()", "()(())(())", "()(())()()", "()()((()))", "()()(()())", "()()(())()", "()()()(())", "()()()()()")
        );
    }

    public List<String> generateParenthesis(int n) {
        Set<String> result = new LinkedHashSet<>();

        char[] arr = ("(".repeat(n) + ")".repeat(n)).toCharArray();

        // symmetric
        // forward: side -> center 1/4
        // reverse: center -> side 1/4

        // asymmetric (while n > 2)
        // forward: () + self(n-2)
        // reverse: self(n-2) + ()

        // reduce envelope pair (while n > 2)
        // ( + self(n-2) + )

        // def
        result.add(String.valueOf(arr));

        // s f
        char[] chars = Arrays.copyOf(arr, arr.length);
        for (int i = 1; i < n; i += 2) {
            chars[i] = flip(chars[i]);
            chars[arr.length - 1 - i] = flip(chars[arr.length - 1 - i]);

            if (!isValid(chars)) {
                //System.out.println("! invalid: " + Arrays.toString(chars));
                continue;
            }

            result.add(String.valueOf(chars));
        }

        // s r
        chars = Arrays.copyOf(arr, arr.length);
        for (int i = n; i > 0; i -= 2) {
            chars[i] = flip(chars[i]);
            chars[arr.length - 1 - i] = flip(chars[arr.length - 1 - i]);

            if (!isValid(chars)) {
                //System.out.println("!! invalid: " + Arrays.toString(chars));
                continue;
            }

            result.add(String.valueOf(chars));
        }

        // as
        int leftN = n - 1;
        while (leftN > 0) {
            List<String> strings1 = generateParenthesis(leftN);
            List<String> strings2 = generateParenthesis(n - leftN);

            for (String s : strings1) {
                for (String s2 : strings2) {
                    String e1 = s2 + s;
                    if (isValid(e1.toCharArray())) {
                        result.add(e1);
                    }
                    String e2 = s + s2;
                    if (isValid(e2.toCharArray())) {
                        result.add(e2);
                    }
                }

                String e3 = "(".repeat(n - leftN) + s + ")".repeat(n - leftN);
                if (isValid(e3.toCharArray())) {
                    result.add(e3);
                }


//                // as f
//                String e1 = "()".repeat(n - leftN) + s;
//                if (!isValid(e1.toCharArray())) {
//                    //System.out.println("!!! invalid: " + e1);
//                } else {
//                    result.add(e1);
//                }
//
//                // as r
//                String e2 = s + "()".repeat(n - leftN);
//                if (!isValid(e2.toCharArray())) {
//                    //System.out.println("!!!! invalid: " + e2);
//                } else {
//                    result.add(e2);
//                }
//
//                // r e
//                String e3 = "(".repeat(n - leftN) + s + ")".repeat(n - leftN);
//                if (!isValid(e3.toCharArray())) {
//                    //System.out.println("!!!!! invalid: " + e3);
//                } else {
//                    result.add(e3);
//                }
//
//                /////////////////
//                String e4 = "(".repeat(n - leftN) + ")".repeat(n - leftN) + s;
//                if (!isValid(e4.toCharArray())) {
//                    //System.out.println("!!! invalid: " + e1);
//                } else {
//                    result.add(e4);
//                }
//
//                String e5 = s+"(".repeat(n - leftN) + ")".repeat(n - leftN);
//                if (!isValid(e5.toCharArray())) {
//                    //System.out.println("!!! invalid: " + e1);
//                } else {
//                    result.add(e5);
//                }
            }
            leftN--;
        }

        return result.stream().toList();
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
