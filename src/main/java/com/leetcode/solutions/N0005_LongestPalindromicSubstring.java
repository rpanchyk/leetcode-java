package com.leetcode.solutions;

import java.util.Arrays;

public class N0005_LongestPalindromicSubstring {

    public static void main(String[] args) {
        System.out.println(new N0005_LongestPalindromicSubstring().longestPalindrome("babad"));
        System.out.println(new N0005_LongestPalindromicSubstring().longestPalindrome("cbbd"));
        System.out.println(new N0005_LongestPalindromicSubstring().longestPalindrome("bb"));
        System.out.println(new N0005_LongestPalindromicSubstring().longestPalindrome("bab"));
        System.out.println(new N0005_LongestPalindromicSubstring().longestPalindrome("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"));
        System.out.println(new N0005_LongestPalindromicSubstring().longestPalindrome("ccd"));
        System.out.println(new N0005_LongestPalindromicSubstring().longestPalindrome("qbmhukucteihghldwdobtvgwwnhflpceiwhbkmvxavmqxedfndegztlpjptpdowwavemasyrjxxnhldnloyizyxgqlhejsdylvkpdzllrzoywfkcamhljminikvwwvqlerdilrdgzifojjlgeayprejhaequyhcohoeonagsmfrqhfzllobwjhxdxzadwxiglvzwiqyzlnamqqsastxlojpcsleohgtcuzzrvwzqugyimaqtorkafyebrgmrfmczwiexdzcokbqymnzigifbqzvfzjcjuugdmvegnvkgbmdowpacyspszvgdapklrhlhcmwkwwqatfswmxyfnxkepdotnvwndjrcclvewomyniaefhhcqkefkyovqxyswqpnysafnydbiuanqphfhfbfovxuezlovokrsocdqrqfzbqhntjafzfjisexcdlnjbhwrvnyabjbshqsxnaxhvtmqlfgdumtpeqzyuvmbkvmmdtywquydontkugdayjqewcgtyajofmbpdmykqobcxgqivmpzmhhcqiyleqitojrrsknhwepoxawpsxcbtsvagybnghqnlpcxlnshihcjdjxxjjwyplnemojhodksckmqdvnzewhzzuswzctnlyvyttuhlreynuternbqonlsfuchxtsyhqlvifcxerzqepthwqrsftaquzuxwcmjjulvjrkatlfqshpyjsbaqwawgpabkkjrtchqmriykbdsxwnkpaktrvmxjtfhwpzmieuqevlodtroiulzgbocrtiuvpywtcxvajkpfmaqckgrcmofkxynjxgvxqvkmhdbvumdaprijkjxxvpqnxakiavuwnifvyfolwlekptxbnctjijppickuqhguvtoqfgepcufbiysfljgmfepwyaxusvnsratn"));
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        char[] arr = s.toCharArray();

        String result = null;
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length; j >= i; j--) {

                char[] sliced = Arrays.copyOfRange(arr, i, j);
                if (isPalindromic(sliced)) {
                    result = result == null || sliced.length > result.length() ? String.valueOf(sliced) : result;

                    // optimize
                    if (s.equals(result)) {
                        break;
                    }
                }
            }

            // optimize
            if (s.equals(result)) {
                break;
            }
        }
        return result;
    }

    private boolean isPalindromic(char[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            if (arr[start] != arr[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

// 1
//    public String longestPalindrome(String s) {
//        if (s == null || s.length() < 2 || isPalindromic(s)) {
//            return s;
//        }
//
//        String result = null;
//        for (int i = 0; i < s.length(); i++) {
//            for (int j = s.length(); j >= i; j--) {
//                String substring = s.substring(i, j);
//                if (isPalindromic(substring)) {
//                    result = result == null || substring.length() > result.length() ? substring : result;
//
//                    // optimize
//                    if (s.equals(result)) {
//                        break;
//                    }
//                }
//            }
//
//            // optimize
//            if (s.equals(result)) {
//                break;
//            }
//        }
//        return result;
//    }
//
//    private boolean isPalindromic(String s) {
//        return new StringBuilder(s).reverse().compareTo(new StringBuilder(s)) == 0;
//    }
}
