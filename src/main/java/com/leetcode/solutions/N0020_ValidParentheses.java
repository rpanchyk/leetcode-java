package com.leetcode.solutions;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import static com.leetcode.utils.AssertUtils.assertThat;

public class N0020_ValidParentheses {

    public static void main(String[] args) {
        assertThat(new N0020_ValidParentheses().isValid("()"), true);
        assertThat(new N0020_ValidParentheses().isValid("()[]{}"), true);
        assertThat(new N0020_ValidParentheses().isValid("(]"), false);
    }

    private static final Map<Character, Character> map = new HashMap<>() {{
        put('(', ')');
        put('[', ']');
        put('{', '}');
    }};

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }

        final Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) { // is open
                stack.push(c);
            } else { // is closed
                if (stack.isEmpty()) {
                    return false;
                }
                final char open = stack.pop();
                if (c != map.get(open)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
