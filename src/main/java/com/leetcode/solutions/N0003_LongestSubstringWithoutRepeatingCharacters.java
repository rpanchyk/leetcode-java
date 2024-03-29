package com.leetcode.solutions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class N0003_LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(new N0003_LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new N0003_LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("bbbbb"));
        System.out.println(new N0003_LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("pwwkew"));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) {
            return 0;
        }

        List<String> list = new ArrayList<>();
        int result = 0;

        for (String c : s.split("")) {
            if (list.contains(c)) {
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    String n = it.next();
                    it.remove();
                    if (n.equals(c)) {
                        break;
                    }
                }
            }
            list.add(c);

            int size = list.size();
            result = Math.max(size, result);
        }
        return result;
    }
}
