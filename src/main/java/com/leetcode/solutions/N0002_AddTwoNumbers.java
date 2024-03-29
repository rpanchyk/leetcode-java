package com.leetcode.solutions;

public class N0002_AddTwoNumbers {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return val
                + (next == null ? "" : ",")
                + (next == null ? "" : next.toString());
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(1,
            new ListNode(9,
                new ListNode(9,
                    new ListNode(9,
                        new ListNode(9,
                            new ListNode(9,
                                new ListNode(9,
                                    new ListNode(9,
                                        new ListNode(9,
                                            new ListNode(9)
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            )
        );

        System.out.println(new N0002_AddTwoNumbers().addTwoNumbers(l1, l2));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int val = l1.val + l2.val;
        int valNorm = val >= 10 ? val % 10 : val;
        ListNode parent = new ListNode(valNorm);
        parent.next = add(l1.next, l2.next, val >= 10);
        return parent;
    }

    public ListNode add(ListNode l1, ListNode l2, boolean plus) {
        if (l1 == null && l2 == null) {
            return plus ? new ListNode(1) : null;
        }
        int v1 = l1 != null ? l1.val : 0;
        int v2 = l2 != null ? l2.val : 0;
        int val = v1 + v2 + (plus ? 1 : 0);
        int valNorm = val >= 10 ? val % 10 : val;

        ListNode node = new ListNode(valNorm);
        node.next = add(l1 != null ? l1.next : null, l2 != null ? l2.next : null, val >= 10);
        return node;
    }
}
