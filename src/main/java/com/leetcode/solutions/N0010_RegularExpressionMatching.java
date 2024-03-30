package com.leetcode.solutions;

import static com.leetcode.solutions.Assert.assertThat;

public class N0010_RegularExpressionMatching {

    public static void main(String[] args) {
//        assertThat(new N0010_RegularExpressionMatching().isMatch("aa", "aa"), true);
//        assertThat(new N0010_RegularExpressionMatching().isMatch("aa", "a"), false);
//        assertThat(new N0010_RegularExpressionMatching().isMatch("aa", "a*"), true);
//        assertThat(new N0010_RegularExpressionMatching().isMatch("ab", ".*"), true);
//        assertThat(new N0010_RegularExpressionMatching().isMatch("aab", "c*a*b"), true);
//        assertThat(new N0010_RegularExpressionMatching().isMatch("aaa", ".a"), false);
//        assertThat(new N0010_RegularExpressionMatching().isMatch("mississippi", "mis*is*ip*."), true);
//        assertThat(new N0010_RegularExpressionMatching().isMatch("abcd", "d*"), false);
//        assertThat(new N0010_RegularExpressionMatching().isMatch("aaa", "aaaa"), false);
//        assertThat(new N0010_RegularExpressionMatching().isMatch("aaa", "ab*a*c*a"), true);
//        assertThat(new N0010_RegularExpressionMatching().isMatch("aaa", "ab*a"), false);
//        assertThat(new N0010_RegularExpressionMatching().isMatch("aaca", "ab*a*c*a"), true);
//        assertThat(new N0010_RegularExpressionMatching().isMatch("a", "ab*a"), false);
//        assertThat(new N0010_RegularExpressionMatching().isMatch("ab", ".*.."), true);
        assertThat(new N0010_RegularExpressionMatching().isMatch("aabcbcbcaccbcaabc", ".*a*aa*.*b*.c*.*a*"), true);
        assertThat(new N0010_RegularExpressionMatching().isMatch("a", ".*..a*"), false);
        assertThat(new N0010_RegularExpressionMatching().isMatch("acaabbaccbbacaabbbb", "a*.*b*.*a*aa*a*"), false);
    }

    public boolean isMatch(String s, String p) {
        if (!p.contains(".") && !p.contains("*")) {
            return s.equals(p);
        }

        if (compact(p).length() > s.length()) {
            return false;
        }

        p = compactPattern(p);

        int sIndex = 0;
        int pIndex = 0;

        while (sIndex < s.length() && pIndex < p.length()) {
            String substring1 = p.substring(pIndex);
            String compacted = compact(substring1);
            String substring = s.substring(sIndex);
            if (!compacted.equals(substring1) && isMatch(substring, compacted)) {
                return true;
            }

            boolean greedy = pIndex < p.length() - 1 && p.charAt(pIndex + 1) == '*';

            char sChar = s.charAt(sIndex);
            char pChar = p.charAt(pIndex);
            if (pChar == '.') {
                pChar = sChar;
            }

            if (greedy) {
                if (sChar != pChar) {
                    String newS = s.substring(sIndex);
                    String newP = p.substring(pIndex + 2);
                    return isMatch(newS, newP);
                }

                sIndex++;
//                if (sChar != pChar) {
//                    pIndex++;
//                    pIndex++;
//                }
            } else if (sChar == pChar) {
                sIndex++;
                pIndex++;
            } else {
                return false;
            }
        }

        if (sIndex == s.length()) {
            String compacted = compact(p.substring(pIndex));
            if (!s.substring(sIndex).equals(compacted)) {
                return false;
            }
        }

        return sIndex == s.length();
    }

    private String compactPattern(String p) {
        int i = 0;
        while (i < p.length() - 3) {
            char curr = p.charAt(i);
            if (curr != '*') {
                boolean wild = p.charAt(i + 1) == '*';
                if (wild) {
                    char next = p.charAt(i + 2);
                    if (curr == next && p.charAt(i + 3) != '*') {
                        String s1 = p.substring(0, i + 1);
                        String s2 = p.substring(i + 3);
//                        String res = s1 + "*" + s2;
                        String res = p.substring(0, i) + curr + next + '*' + p.substring(i + 3);
                        res = res.replace("**", "*");
                        return compactPattern(res);
                    }
                }
            }
            i++;
        }
        return p;
    }

    private String compact(String s) {
        StringBuilder res = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            boolean greedy = i < s.length() - 1 && s.charAt(i + 1) == '*';
            if (greedy) {
                i++;
                i++;
            } else {
                res.append(s.charAt(i));
                i++;
            }
        }
        return res.toString();
    }

    public boolean isMatch3(String s, String p) {
        if (!p.contains(".") && !p.contains("*")) {
            return s.equals(p);
        }

        int sIndex = s.length() - 1;
        int pIndex = p.length() - 1;

        while (sIndex >= 0 && pIndex >= 0) {
            char sChar = s.charAt(sIndex);
            char pChar = p.charAt(pIndex);

            if (pChar == '.') {
                pChar = sChar;
            }

            if (pChar == '*') {
                pIndex--;
                pChar = p.charAt(pIndex);
                if (pChar == '.') {
                    pChar = sChar;
                }

                if (sChar != pChar) {
                    pIndex--;
                } else {
//                    if (!s.substring(0, sIndex).contains(String.valueOf(pChar))) {
//                        pIndex++;
//                    } else {
//                        if (pIndex > 0)
//                        pIndex--;
//                    }
                    pIndex++;
                    sIndex--;
                }
            } else {
                if (sChar != pChar) {
                    return false;
                }
                pIndex--;
                sIndex--;
            }
        }

        if (sIndex < 0 && pIndex >= 0) {
            String res = p.substring(0, pIndex + 1);
            for (int i = res.length() - 1; i >= 0; i--) {
                if (res.charAt(i) == '*') {
                    if (i > 1) {
                        res = res.substring(0, i - 1);
                        i--;
                    } else {
                        res = "";
                        break;
                    }
                }
            }
            return res.isEmpty();
//            return isMatch("", p.substring(0, pIndex+1));
        }

        return sIndex < 0;
    }

    public boolean isMatch2(String s, String p) {
        if (!p.contains(".") && !p.contains("*")) {
            return s.equals(p);
        }

        boolean wild = false;
        int pIndex = p.length() - 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (pIndex < 0) {
                return false;
            }
//            if (pIndex == 0 && i > 0 && !wild) {
//                return false;
//            }
            char sChar = s.charAt(i);
            char pChar = p.charAt(pIndex);

            if (pChar == '*') {
                wild = true;
                while (true) {
                    pIndex--;
                    pChar = p.charAt(pIndex);
//                    if (pChar == '*') {
//                        continue;
//                    }
                    if (pChar == '.') {
                        pChar = sChar;
//                        break;
                    }
                    if (sChar != pChar) {
//                        wild = false;
                        //pIndex--;
                        continue;
//                        pIndex--;
//                        break;
                    }

//                    if (p.charAt(pIndex) == '.')
//                    pIndex--;
                    break;
                }
            } else {
                wild = false;
                if (pChar == '.') {
                    pChar = sChar;
                }
                pIndex--;
            }

//            if (pIndex > 0) {
//            pIndex--;
//            }
//            if (pIndex < 0) {
//                return false;
//            }

//            if (pChar == '*') {
//                wild = true;
//                pChar = p.charAt(pIndex - 1);
//                if (pChar == '.') {
//                    pChar = sChar;
//                }
//                if (sChar != pChar) {
//                    pIndex--;
//                    pIndex--;
//                    if (pIndex < 0) {
//                        return false;
//                    }
//                    pChar = p.charAt(pIndex);
//                    pIndex--;
//                }
//            } else {
//                if (pChar == '.') {
//                    pChar = sChar;
//                }
//                pIndex--;
//                wild = false;
//            }

//            if (pIndex < 0) {
//                return false;
//            }

            if (sChar != pChar) {
                return false;
            }
//            if (pIndex == 0 && i != 0) {
//                return false;
//            }
        }

        return true;
    }


//        if (!p.contains(".") && p.contains("*")) {
//            String p1 = p.substring(0, p.indexOf("*"));
//
//            int pIndex = 0;
//            for (int i = 0; i < s.length(); i++) {
//                char pChar = pIndex < p1.length() ? p1.charAt(pIndex) : p1.charAt(p1.length() - 1);
//                if (s.charAt(i) != pChar) {
//                    return false;
//                }
//                pIndex++;
//            }
//        }
}
