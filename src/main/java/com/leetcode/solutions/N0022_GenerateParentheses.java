package com.leetcode.solutions;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.leetcode.utils.AssertUtils.assertThat;

public class N0022_GenerateParentheses {

    public static void main(String[] args) {
//        assertThat(new N0022_GenerateParentheses().generateParenthesis(1), List.of("()"));
//        assertThat(new N0022_GenerateParentheses().generateParenthesis(2), List.of("(())","()()"));
//        assertThat(new N0022_GenerateParentheses().generateParenthesis(3), List.of("((()))", "(()())", "(())()", "()(())", "()()()"));
        assertThat(new N0022_GenerateParentheses().generateParenthesis(4), List.of("(((())))", "((()()))", "((())())", "((()))()", "(()(()))", "(()()())", "(()())()", "(())(())", "(())()()", "()((()))", "()(()())", "()(())()", "()()(())", "()()()()"));
//        assertThat(new N0022_GenerateParentheses().generateParenthesis(5), List.of("((((()))))", "(((()())))", "(((())()))", "(((()))())", "(((())))()", "((()(())))", "((()()()))", "((()())())", "((()()))()", "((())(()))", "((())()())", "((())())()", "((()))(())", "((()))()()", "(()((())))", "(()(()()))", "(()(())())", "(()(()))()", "(()()(()))", "(()()()())", "(()()())()", "(()())(())", "(()())()()", "(())((()))", "(())(()())", "(())(())()", "(())()(())", "(())()()()", "()(((())))", "()((()()))", "()((())())", "()((()))()", "()(()(()))", "()(()()())", "()(()())()", "()(())(())", "()(())()()", "()()((()))", "()()(()())", "()()(())()", "()()()(())", "()()()()()"));
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

        return result.stream().toList();
    }
}
