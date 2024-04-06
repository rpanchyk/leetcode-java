package com.leetcode.solutions;

import java.util.Arrays;
import java.util.List;

import static com.leetcode.utils.AssertUtils.assertThat;

public class N0017_LetterCombinationsOfPhoneNumber {

    public static void main(String[] args) {
        assertThat(new N0017_LetterCombinationsOfPhoneNumber().letterCombinations(""), List.of());
        assertThat(new N0017_LetterCombinationsOfPhoneNumber().letterCombinations("2"), List.of("a", "b", "c"));
        assertThat(new N0017_LetterCombinationsOfPhoneNumber().letterCombinations("23"), List.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"));
        assertThat(new N0017_LetterCombinationsOfPhoneNumber().letterCombinations("234"), List.of(
            "adg", "adh", "adi", "aeg", "aeh", "aei", "afg", "afh", "afi",
            "bdg", "bdh", "bdi", "beg", "beh", "bei", "bfg", "bfh", "bfi",
            "cdg", "cdh", "cdi", "ceg", "ceh", "cei", "cfg", "cfh", "cfi"));
    }

    public List<String> letterCombinations(String digits) {
        String[] result = {};
        for (char c : digits.toCharArray()) {
            result = multiply(result, letters(c));
        }
        return Arrays.asList(result);
    }

    private String[] letters(char c) {
        return switch (c) {
            case '2' -> new String[]{"a", "b", "c"};
            case '3' -> new String[]{"d", "e", "f"};
            case '4' -> new String[]{"g", "h", "i"};
            case '5' -> new String[]{"j", "k", "l"};
            case '6' -> new String[]{"m", "n", "o"};
            case '7' -> new String[]{"p", "q", "r", "s"};
            case '8' -> new String[]{"t", "u", "v"};
            case '9' -> new String[]{"w", "x", "y", "z"};
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }

    private String[] multiply(String[] arr1, String[] arr2) {
        if (arr1.length == 0 || arr2.length == 0) {
            return arr1.length == 0 ? arr2 : arr1;
        }
        String[] result = new String[arr1.length * arr2.length];
        int idx = 0;
        for (String a1 : arr1) {
            for (String a2 : arr2) {
                result[idx++] = a1 + a2;
            }
        }
        return result;
    }
}
