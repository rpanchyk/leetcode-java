package com.leetcode.solutions;

import static com.leetcode.utils.AssertUtils.assertThat;

public class N0012_IntegerToRoman {

    public static void main(String[] args) {
        assertThat(new N0012_IntegerToRoman().intToRoman(3), "III");
        assertThat(new N0012_IntegerToRoman().intToRoman(58), "LVIII");
        assertThat(new N0012_IntegerToRoman().intToRoman(1994), "MCMXCIV");
    }

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int dim = 1;
        int rem = num;

        while (rem != 0) {
            int n = rem % 10 * dim;

            String s = null;
            switch (n) {
                case 4:
                    s = "IV";
                    break;
                case 9:
                    s = "IX";
                    break;
                case 40:
                    s = "XL";
                    break;
                case 90:
                    s = "XC";
                    break;
                case 400:
                    s = "CD";
                    break;
                case 900:
                    s = "CM";
                    break;
                default:
                    break;
            }

            if (s == null) {
                int n2 = n / dim;
                int diff = 0;
                StringBuilder sb1 = new StringBuilder();

                if (dim == 1000) {
                    sb1.append("M".repeat(Math.max(0, n2)));
                    s = sb1.toString();
                } else if (dim == 100) {
                    if (n2 >= 5) {
                        sb1.append("D");
                        diff = 5;
                    }
                    sb1.append("C".repeat(Math.max(0, n2 - diff)));
                    s = sb1.toString();
                } else if (dim == 10) {
                    if (n2 >= 5) {
                        sb1.append("L");
                        diff = 5;
                    }
                    sb1.append("X".repeat(Math.max(0, n2 - diff)));
                    s = sb1.toString();
                } else if (dim == 1) {
                    if (n2 >= 5) {
                        sb1.append("V");
                        diff = 5;
                    }
                    sb1.append("I".repeat(Math.max(0, n2 - diff)));
                    s = sb1.toString();
                }
            }

            sb.insert(0, s);

            dim *= 10;
            rem /= 10;
        }
        return sb.toString();
    }
}
