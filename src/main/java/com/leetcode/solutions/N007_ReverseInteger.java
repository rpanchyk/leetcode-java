package com.leetcode.solutions;

public class N007_ReverseInteger {

    public static void main(String[] args) {
        System.out.println(new N007_ReverseInteger().reverse(123));
        System.out.println(new N007_ReverseInteger().reverse(-123));
        System.out.println(new N007_ReverseInteger().reverse(120));
        System.out.println(new N007_ReverseInteger().reverse(1534236469));
        System.out.println(new N007_ReverseInteger().reverse(10));
    }

    public int reverse(int x) {
        if (x < 10 && x > -10) {
            return x;
        }

        long number = x >= 0 ? x : -x;
        long dim = 10;
        while (number >= 10) {
            number /= 10;
            dim *= 10;
        }

        number = x >= 0 ? x : -x;
        long result = 0;
        while (number > 0) {
            dim /= 10;
            result += (number % 10) * dim;
            number /= 10;
        }
        if (result > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) (x >= 0 ? result : -result);

// 1
//        String s = String.valueOf(x >= 0 ? x : -x);
//        char[] array = s.toCharArray();
//        for (int i = 0; i < array.length / 2; i++) {
//            char c = array[i];
//            array[i] = array[array.length - 1 - i];
//            array[array.length - 1 - i] = c;
//        }
//        String result = String.valueOf(array);
//        try {
//            int i = Integer.parseInt(result);
//            return x >= 0 ? i : -i;
//        } catch (NumberFormatException e) {
//            return 0;
//        }
    }
}
