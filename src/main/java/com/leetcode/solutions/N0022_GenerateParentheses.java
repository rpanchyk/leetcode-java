package com.leetcode.solutions;

import java.util.*;

import static com.leetcode.utils.AssertUtils.assertThat;

public class N0022_GenerateParentheses {

    public static void main(String[] args) {
//        assertThat(new N0022_GenerateParentheses().generateParenthesis(1), List.of("()"));
//        assertThat(new N0022_GenerateParentheses().generateParenthesis(2), List.of("(())","()()"));
//        assertThat(new N0022_GenerateParentheses().generateParenthesis(3), List.of("((()))", "(()())", "(())()", "()(())", "()()()"));
        assertThat(new N0022_GenerateParentheses().generateParenthesis(4), List.of("(((())))", "((()()))", "((())())", "((()))()", "(()(()))", "(()()())", "(()())()", "(())(())", "(())()()", "()((()))", "()(()())", "()(())()", "()()(())", "()()()()"));
    }

    public List<String> generateParenthesis(int n) {
        Set<String> result = new LinkedHashSet<>();
        for (int i = 0; i < n; i++) {
            int eggs = i + 1;
            int coats = n - eggs;
            System.out.printf("%d %d %d\n", n, eggs, coats);

            String[] arr = new String[eggs];
//            Arrays.fill(arr, "()");
            for (int j = 0; j < arr.length; j++) {
//                arr[j] = "("+j+")";
                arr[j] = "()";
            }

            List<String> gen = gen6(arr, coats);
            //System.out.println(gen);
            result.addAll(gen);
        }
        return result.stream().toList();
    }

    private List<String> gen6(String[] arr, int coats) {
        List<String> result = new ArrayList<>();
        System.out.println("orig = " + Arrays.toString(arr));

        // left: start -> i
        // right: i -> end
        for (int i = 0; i < arr.length; i++) {
            String[] left = new String[i + 1];
            System.arraycopy(arr, 0, left, 0, i + 1);
            System.out.println("left = " + Arrays.toString(left));
//            String[] leftFull = Arrays.copyOf(left, arr.length);
//            System.arraycopy(arr, i, leftFull, i, arr.length - i);
//            System.out.println("leftFull = " + Arrays.toString(leftFull));

            String[] right = new String[arr.length - 1 - i];
            System.arraycopy(arr, i + 1, right, 0, right.length);
            System.out.println("right = " + Arrays.toString(right));
//            String[] rightFull = new String[arr.length];
//            System.arraycopy(arr, 0, rightFull, 0, i);
//            System.arraycopy(right, 0, rightFull, i, right.length);
//            System.out.println("rightFull = " + Arrays.toString(rightFull));

            for (String s : doWork(left, coats)) {
                String e = s + String.join("", right);
//                System.out.println("e="+e);
                result.add(e);
            }

            for (String s : doWork(right, coats)) {
                String e = String.join("", left) + s;
//                System.out.println("e="+e);
                result.add(e);
            }

            int leftCoats = coats - 1;
            while (leftCoats > 0) {
                for (String s : doWork(left, leftCoats)) {
                    String e = getCoats(s + String.join("", right), coats - leftCoats);
                    result.add(e);
                }

                for (String s : doWork(right, leftCoats)) {
                    String e = getCoats(s + String.join("", left), coats - leftCoats);
                    result.add(e);
                }

                List<String> strings1 = doWork(left, leftCoats);
                List<String> strings2 = doWork(right, coats - leftCoats);
                for (String s1 : strings1) {
                    for (String s2 : strings2) {
                        result.add(s1 + s2);
                    }
                }

                List<String> strings3 = doWork(left, coats - leftCoats);
                List<String> strings4 = doWork(right, leftCoats);
                for (String s3 : strings3) {
                    for (String s4 : strings4) {
                        result.add(s3 + s4);
                    }
                }

                leftCoats--;
            }

//            if (coats >0) {
//                for (String s : doWork(left, coats - 1)) {
//                    String e = getCoats(s + String.join("", right), 1);
////                System.out.println("e="+e);
//                    result.add(e);
//                }
//            }
//
//            if (coats >0) {
//                for (String s : doWork(right, coats-1)) {
//                    String e = getCoats(s + String.join("", left), 1);
////                System.out.println("e="+e);
//                    result.add(e);
//                }
//            }
//
//            if (coats >1) {
//                List<String> strings1 = doWork(left, coats - 1);
//                List<String> strings2 = doWork(right, coats - 1);
//                for (String s1 : strings1) {
//                    for (String s2 : strings2) {
//                        result.add(s1+s1);
//                    }
//                }
//            }

//            String s = doWork(left, coats) + String.join("", right);
//            result.add(s);

            System.out.println();
        }

        return result;
    }

    private List<String> doWork(String[] arr, int coats) {
        if (arr.length == 0) {
            return List.of();
        }

        // each item
        List<String> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            String[] left = new String[i];
            System.arraycopy(arr, 0, left, 0, i);

            String s = getCoats(arr[i], coats);

            String[] right = new String[arr.length - 1 - i];
            System.arraycopy(arr, i + 1, right, 0, right.length);

            String e = String.join("", left) + s + String.join("", right);
            result.add(e);

            String e1 = getCoats(String.join("", left), coats) + arr[i] + String.join("", right);
            result.add(e1);

            String e2 = String.join("", left) + arr[i] + getCoats(String.join("", right), coats);
            result.add(e2);

            String e3 = getCoats(String.join("", left) + arr[i], coats) + String.join("", right);
            result.add(e3);

            String e4 = String.join("", left) + getCoats(arr[i] + String.join("", right), coats);
            result.add(e4);

        }

        // all items
        String s = getCoats(String.join("", arr), coats);
        result.add(s);

        return result;
    }

    private List<String> gen5(String[] arr, int coats) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
//            int leftCoats = coats;
            for (int c = 1; c <= coats; c++) { // wear i-th egg with c number of coats
                int counter = 0;

//                if (c+i+2 < arr.length + coats) {
//                    continue;
//                }

                String s = "";
                if (i > 0) {
                    for (int j = 0; j < i; j++) {
                        s += arr[j];
                        counter++;
                    }
                }

//                String s1 = arr[i];
//                int leftCoats = c;
//                while (leftCoats > 0) {
//                    s1 = getCoats(s1, 1);
//                    leftCoats--;
//                }
//                s += s1;
                s += getCoats(arr[i], c);
                counter += c;

                if (i + 1 < arr.length) {

                    int cnt = coats - c;
                    if (cnt > 0) {

                        String[] copy = Arrays.copyOf(arr, i + 1);
                        List<String> strings = gen5(copy, cnt);

                        for (String string : strings) {
                            result.add(s + string);
                        }
                    }

                    for (int j = i + 1; j < arr.length; j++) {
                        s += arr[j];
                        counter++;
                    }
                }

                if (counter + 1 < arr.length + coats) {
                    continue;
                }

                result.add(s);
//                leftCoats--;
            }
        }

        String joined = String.join("", arr);
        String s = getCoats(joined, coats);
        result.add(s);

        if (coats > 0) {
            List<String> strings = gen5(arr, coats - 1);
            for (String string : strings) {
                result.add(getCoats(string, 1));
            }
        }

        return result;
    }

    private static class Node {
        Node parent;
        Node[] children;

        public Node(Node parent, Node[] children) {
            this.parent = parent;
            this.children = children;
        }

        public Node addChild(Node child) {
            if (children == null) {
                children = new Node[]{child};
            } else {
                children = Arrays.copyOf(children, children.length + 1);
                children[children.length - 1] = child;
            }
            return this;
        }

        @Override
        public String toString() {
            if (children == null || children.length == 0) {
                return "()";
            }
            StringBuilder sb = new StringBuilder();
            for (Node child : children) {
                sb.append(child.toString());
            }
            return "(" + sb + ")";
        }
    }

    public List<String> generateParenthesis3(int n) {
        List<String> result = new ArrayList<>();

//        String s = "(((())))";
//        String s = "(".repeat(n * 2);
//        for (int i = 0; i < n*2; i++) {
//            for (int j = i; j < n*2; j++) {
//                String s1 = s.substring(0, j) + flip(s.charAt(j)) + s.substring(j + 1);
//                if (isValid(s1)) {
//                    result.add(s1);
//                }
//            }
//        }

//        String[] arr = new String[n];
//        Arrays.fill(arr, "()");

        // My
        // (((())))
        // ((()()))
        // ((())()), ((()))(), (()(())),
        // (()()()),
        // (()())(), (())(()),
        // (())()(), ()((())), ()(()()), ()(())(), ()()(()),
        // ()()()()

        return result;
    }

    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count = (c == '(') ? count + 1 : count - 1;
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    private char flip(char c) {
        return c == '(' ? ')' : '(';
    }

    public List<String> generateParenthesis2(int n) {
        Set<String> result = new LinkedHashSet<>();
        for (int i = 0; i < n; i++) {
            int eggs = i + 1;
            int coats = n - eggs;
            System.out.printf("%d %d %d\n", n, eggs, coats);

            String[] arr = new String[eggs];
            Arrays.fill(arr, "()");

            List<String> gen = gen4(arr, coats);
            System.out.println(gen);
            result.addAll(gen);
        }
        return result.stream().toList();
    }

    private List<String> gen4(String[] arr, int coats) {
        List<String> result = new ArrayList<>();


        return result;
    }

    private List<String> gen3(String[] arr, int coats) {
        List<String> result = new ArrayList<>();


//        if (coats-arr.length == 1) {
//            String s = String.join("",arr);
//            result.add("(" + s + ")");
//        }

//        while (arr.length > 0) {
        if (coats > 0) {
            int leftCoats = coats;
            String s = String.join("", arr);
            while (leftCoats > 0) {
                s = "(" + s + ")";
                leftCoats--;
            }
            result.add(s);
        }

        for (int i = 0; i < arr.length; i++) {
            String s = "";
            for (int j = 0; j < i; j++) {
                s += arr[j];
            }

            s += getCoats(arr[i], coats);

            for (int j = i + 1; j < arr.length; j++) {
                s += arr[j];
            }
            result.add(s);
        }
//            if (arr.length > 1) {
//                arr[1] = arr[0] + arr[1];
//                arr = Arrays.copyOfRange(arr, 1, arr.length);
//                result.addAll(gen3(arr, coats));
//            } else {
//                break;
//            }
//        }

//        while (arr.length > 1 && coats > 0) {
//            arr[1] = arr[0] + arr[1];
//            arr = Arrays.copyOfRange(arr, 1, arr.length);
//            result.addAll(gen3(arr, coats-1));
//        }

        return result;
    }

    private List<String> gen(int eggs, int coats) {
        List<String> result = new ArrayList<>();

//        if (coats == 0) {
//            result.add("()".repeat(eggs));
//            return result;
//        }

//        if (eggs == 1) {
//            result.add(getCoats("()", coats));
//        } else {

        String[] arr = new String[eggs];
        Arrays.fill(arr, "()");

//            for (int i = 0; i < arr.length; i++) {
//                String s = "";
//                for (int j = 0; j < i; j++) {
//                    s += arr[j];
//                }
//
//                s += getCoats(arr[i], coats);
//
//                for (int j = i + 1; j < arr.length; j++) {
//                    s += arr[j];
//                }
//                result.add(s);
//            }

        result.addAll(gen2(arr, coats));

//            while (arr.length > 1) {
//                arr[1] = arr[0] + arr[1];
//                arr = Arrays.copyOfRange(arr, 1, arr.length);
//
//                result.addAll(gen2(arr, coats));
//            }


//            for (String x : arr) {
//                s += x;
//            }

//            int leftCoats = coats;
//            while (leftCoats > 0) {
//
//
//
//                leftCoats--;
//            }


//        }


//            String s = "";
//            for (int j = 0; j < coats; j++) {
//                s += "(";
//            }
//            s += "()";
//            for (int j = 0; j < coats; j++) {
//                s += ")";
//            }

//        String s = "";
//        int leftCoats = coats;
//        for (int i = 0; i < eggs; i++) {
//
////            if (coats > 0 && coats < eggs) {
////
////            }
//
//            for (int j = 0; j < leftCoats; j++) {
//                s += "(";
//            }
//
//            s += "()";
//
//            for (int j = 0; j < leftCoats; j++) {
//                s += ")";
//            }
//
//            leftCoats--;
//        }
//        result.add(s);

        return result;
    }

    private String getCoats(String s, int coats) {
        return "(".repeat(coats) + s + ")".repeat(coats);
    }

    private List<String> gen2(String[] arr, int coats) {
        List<String> result = new ArrayList<>();

        if (coats > 0 && arr.length > 0) {
            int leftCoats = coats;
            while (leftCoats > 0) {

                result.add("(" + String.join("", arr) + ")");

                leftCoats--;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            String s = "";
            for (int j = 0; j < i; j++) {
                s += arr[j];
            }

            s += getCoats(arr[i], coats);

            for (int j = i + 1; j < arr.length; j++) {
                s += arr[j];
            }
            result.add(s);
        }
        return result;
    }

    //        number index nested
    // ()()() 3 0 0
    // (()()) 3 0 1
    // (())() 3 0 2
    // ((())) 3 0 3
    // ()(()) 3 1 2

    public List<String> generateParenthesis1(int n) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                if (n - i < j) {
                    continue;
                }
                System.out.printf("%d %d %d = %s\n", n, i, j, generate(n, j, i));
            }
        }


//        for (int i = n; i >= 0; i--) {
//            for (int j = 0; j <= n - i; j++) {
//                result.add(generate(n, i, j));
//                System.out.printf("%d %d %d = %s", n, i, j, generate(n, i, j));
//                System.out.println();
//                if (i == 1) {
//                    break;
//                }
//            }
//            if (i == 1) {
//                break;
//            }
//        }
        return result;
    }

//    private String generate(int n, int nested) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < nested; i++) {
//            sb.insert(0, "(");
//            sb.append(")");
//        }
//        sb.append("()".repeat(Math.max(0, n - nested)));
//        return sb.toString();
//    }

    // 3 3 0
    // 3 2 0
    // 3 2 1
    // 3 1 0
    // 3 1 1
    // 3 1 2

    private String generate(int n, int nested, int index) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= n - nested; i++) {
            if (i == index) {
                sb.append(generateNested(nested));
            } else {
                sb.append("()");
            }
        }


//        String generatedNested = generateNested(nested);
//
//        for (int i = 0; i < nested; i++) {
//            sb.insert(0, "(");
//            sb.append(")");
//        }
//        sb.append("()".repeat(Math.max(0, n - nested)));
        return sb.toString();
    }

    private String generateNested(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.insert(0, "(");
            sb.append(")");
        }
        return sb.toString();
    }
}
