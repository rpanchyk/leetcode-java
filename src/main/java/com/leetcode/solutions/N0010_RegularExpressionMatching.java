package com.leetcode.solutions;

import static com.leetcode.solutions.Assert.assertThat;

public class N0010_RegularExpressionMatching {

    public static void main(String[] args) {
        assertThat(new N0010_RegularExpressionMatching().isMatch("aa", "aa"), true); // 1
        assertThat(new N0010_RegularExpressionMatching().isMatch("aa", "a"), false); // 2
        assertThat(new N0010_RegularExpressionMatching().isMatch("aa", "a*"), true); // 3
        assertThat(new N0010_RegularExpressionMatching().isMatch("ab", ".*"), true); // 4
        assertThat(new N0010_RegularExpressionMatching().isMatch("aab", "c*a*b"), true); // 5
        assertThat(new N0010_RegularExpressionMatching().isMatch("aaa", ".a"), false); // 6
        assertThat(new N0010_RegularExpressionMatching().isMatch("mississippi", "mis*is*ip*."), true); // 7
        assertThat(new N0010_RegularExpressionMatching().isMatch("abcd", "d*"), false); // 8
        assertThat(new N0010_RegularExpressionMatching().isMatch("aaa", "aaaa"), false); // 9
        assertThat(new N0010_RegularExpressionMatching().isMatch("aaa", "ab*a*c*a"), true); // 10
        assertThat(new N0010_RegularExpressionMatching().isMatch("aaa", "ab*a"), false); // 11
        assertThat(new N0010_RegularExpressionMatching().isMatch("aaca", "ab*a*c*a"), true); // 12
        assertThat(new N0010_RegularExpressionMatching().isMatch("a", "ab*a"), false); // 13
        assertThat(new N0010_RegularExpressionMatching().isMatch("ab", ".*.."), true); // 14
        assertThat(new N0010_RegularExpressionMatching().isMatch("aabcbcbcaccbcaabc", ".*a*aa*.*b*.c*.*a*"), true); // 15
        assertThat(new N0010_RegularExpressionMatching().isMatch("a", ".*..a*"), false); // 16
        assertThat(new N0010_RegularExpressionMatching().isMatch("acaabbaccbbacaabbbb", "a*.*b*.*a*aa*a*"), false); // 17
        assertThat(new N0010_RegularExpressionMatching().isMatch("aaaaaaaaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*"), false); // 18
    }

    public boolean isMatch(String s, String p) {
        if (s.isEmpty()) {
            return emptyOrGreedy(p);
        }
        if (p.isEmpty()) {
            return false;
        }

        int sIndex = 0;
        int pIndex = 0;

        char sChar = s.charAt(sIndex);
        char pChar = p.charAt(pIndex);
        if (pChar == '.') {
            pChar = sChar;
        }

        boolean greedy = pIndex < p.length() - 1 && p.charAt(pIndex + 1) == '*';
        boolean firstMatch = sChar == pChar;

        if (!greedy) {
            return firstMatch && isMatch(s.substring(sIndex + 1), p.substring(pIndex + 1));
        }

        if (!firstMatch) {
            return isMatch(s, p.substring(pIndex + 2));
        } else {
            return isMatch(s.substring(sIndex + 1), p)
                || isMatch(s, p.substring(pIndex + 2));
        }
    }

    private boolean emptyOrGreedy(String p) {
        int pIndex = 0;
        while (pIndex < p.length()) {
            boolean greedy = pIndex < p.length() - 1 && p.charAt(pIndex + 1) == '*';
            if (!greedy) {
                return false;
            }
            pIndex = pIndex + 2;
        }
        return true;
    }
}
