package com.leetcode.solutions;

import java.math.BigInteger;

public class N0008_StringToIntegerAtoi {

    public static void main(String[] args) {
        System.out.println(new N0008_StringToIntegerAtoi().myAtoi("42"));
        System.out.println(new N0008_StringToIntegerAtoi().myAtoi("   -42"));
        System.out.println(new N0008_StringToIntegerAtoi().myAtoi("4193 with words"));
        System.out.println(new N0008_StringToIntegerAtoi().myAtoi("words and 987"));
        System.out.println(new N0008_StringToIntegerAtoi().myAtoi("-91283472332"));
        System.out.println(new N0008_StringToIntegerAtoi().myAtoi("+1"));
        System.out.println(new N0008_StringToIntegerAtoi().myAtoi("+-12"));
        System.out.println(new N0008_StringToIntegerAtoi().myAtoi("-+12"));
        System.out.println(new N0008_StringToIntegerAtoi().myAtoi("   +0 123"));
        System.out.println(new N0008_StringToIntegerAtoi().myAtoi("-5-"));
        System.out.println(new N0008_StringToIntegerAtoi().myAtoi("-13+8"));
        System.out.println(new N0008_StringToIntegerAtoi().myAtoi("  +  413"));
        System.out.println(new N0008_StringToIntegerAtoi().myAtoi("000+85"));
        System.out.println(new N0008_StringToIntegerAtoi().myAtoi("00000-42a1234"));
    }

    public int myAtoi(String s) {
        StringBuilder sb = new StringBuilder();
        int charFrom = 48; // 0
        int charTo = 57; // 9

        int negative = 1;
        boolean signDefined = false;
        for (char c : s.toCharArray()) {
            if (c == '+' || c == '-') {
                if (!sb.isEmpty() || signDefined) {
                    break;
                }
                if (c == '-') {
                    negative = -1;
                    signDefined = true;
                    continue;
                } else {
                    signDefined = true;
                    continue;
                }
            }

            if (c >= charFrom && c <= charTo) {
                sb.append(c);
                continue;
            }

            if (c == ' ') {
                if (!sb.isEmpty() || signDefined) {
                    break;
                }
                continue;
            }

            break;
        }

        if (sb.isEmpty()) {
            return 0;
        }

        BigInteger bi = new BigInteger(sb.toString());
        BigInteger max = new BigInteger(String.valueOf(Integer.MAX_VALUE));

        if (negative == 1) {
            if (bi.compareTo(max) > 0) {
                bi = max;
            }
        } else {
            BigInteger min = max.add(BigInteger.ONE); // unsigned
            if (bi.compareTo(min) > 0) {
                bi = min;
            }
        }

        return negative * bi.intValue();
    }
}
