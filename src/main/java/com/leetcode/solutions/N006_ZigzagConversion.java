package com.leetcode.solutions;

public class N006_ZigzagConversion {

    public static void main(String[] args) {
        System.out.println(new N006_ZigzagConversion().convert("PAYPALISHIRING", 3));
        System.out.println(new N006_ZigzagConversion().convert("A", 2));
        System.out.println(new N006_ZigzagConversion().convert("AB", 1));
    }

    public String convert(String s, int numRows) {
        char[][] arr = new char[numRows][s.length()];

        int row = 0;
        int col = 0;
        boolean goUp = false;
        for (int i = 0; i < s.length(); i++) {
            arr[row][col] = s.charAt(i);

            if (row == 0) {
                goUp = false;
                col++;
            } else if (row == numRows - 1) {
                goUp = true;
                col++;
            }
            row = goUp ? Math.max(0, row - 1) : Math.min(numRows - 1, row + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (char[] chars : arr) {
            for (char c : chars) {
                if (c != '\0') {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
