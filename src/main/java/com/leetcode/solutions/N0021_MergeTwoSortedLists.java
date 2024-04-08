package com.leetcode.solutions;

import static com.leetcode.utils.AssertUtils.assertThat;

public class N0021_MergeTwoSortedLists {

    public static void main(String[] args) {
        assertThat(new N0021_MergeTwoSortedLists().mergeTwoLists(
                new ListNode(1, new ListNode(2, new ListNode(4))),
                new ListNode(1, new ListNode(3, new ListNode(4)))
            ),
            new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(4))))))
        );
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        final int val1 = list1.val;
        final int val2 = list2.val;

        if (val1 < val2) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }

        list2.next = mergeTwoLists(list1, list2.next);
        return list2;
    }

    // Definition for singly-linked list
    public static class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return toString().equals(String.valueOf(obj)); // draft
        }

        @Override
        public String toString() {
            return val + (next != null ? "," + next : "");
        }
    }
}
