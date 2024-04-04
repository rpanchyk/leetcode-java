package com.leetcode.solutions;

import static com.leetcode.utils.AssertUtils.assertThat;

public class N0014_LongestCommonPrefix {

    public static void main(String[] args) {
        assertThat(new N0014_LongestCommonPrefix().longestCommonPrefix(new String[]{"flower", "flow", "flight"}), "fl");
        assertThat(new N0014_LongestCommonPrefix().longestCommonPrefix(new String[]{"dog", "racecar", "car"}), "");
    }

    public String longestCommonPrefix(String[] strs) {
        int minLength = Integer.MAX_VALUE;
        for (String s : strs) {
            minLength = Math.min(minLength, s.length());
        }

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minLength; i++) {
            boolean ok = true;
            for (int j = 0; j < strs.length - 1; j++) {
                if (strs[j].charAt(i) != strs[j + 1].charAt(i)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                sb.append(strs[0].charAt(i));
            } else {
                break;
            }
        }
        return sb.toString();
    }
}
