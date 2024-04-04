package com.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

import static com.leetcode.utils.AssertUtils.assertThat;

public class N0013_RomanToInteger {

    public static void main(String[] args) {
        assertThat(new N0013_RomanToInteger().romanToInt("III"), 3);
        assertThat(new N0013_RomanToInteger().romanToInt("LVIII"), 58);
        assertThat(new N0013_RomanToInteger().romanToInt("MCMXCIV"), 1994);
    }

    public int romanToInt(String s) {
        int result = 0;

        // 1
//        final Map<String, Integer> map = new HashMap<>(13) {{
//            put("I", 1);
//            put("IV", 4);
//            put("V", 5);
//            put("IX", 9);
//            put("X", 10);
//            put("XL", 40);
//            put("L", 50);
//            put("XC", 90);
//            put("C", 100);
//            put("CD", 400);
//            put("D", 500);
//            put("CM", 900);
//            put("M", 1000);
//        }};
//        for (int i = 0; i < s.length(); i++) {
//            final int endIndex = i == s.length() - 1 ? i + 1 : i + 2;
//            final String candidate = s.substring(i, endIndex);
//
//            final Integer value = map.get(candidate);
//            if (value != null) {
//                i++;
//                result += value;
//            } else {
//                result += map.get(s.substring(i, i + 1));
//            }
//        }

        // 2
        final Map<Character, Integer> map2 = new HashMap<>(7) {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};
        final String normalized = s
            .replace("IV", "IIII")
            .replace("IX", "VIIII")
            .replace("XL", "XXXX")
            .replace("XC", "LXXXX")
            .replace("CD", "CCCC")
            .replace("CM", "DCCCC");

        for (char c : normalized.toCharArray()) {
            result += map2.get(c);
        }

        return result;
    }
}
