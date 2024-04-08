package com.leetcode.solutions;

import static com.leetcode.utils.AssertUtils.assertThat;

public class N0019_RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode listNode2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(5))));
        assertThat(new N0019_RemoveNthNodeFromEndOfList().removeNthFromEnd(listNode1, 2), listNode2);

        assertThat(new N0019_RemoveNthNodeFromEndOfList().removeNthFromEnd(new ListNode(1), 1), null);
        assertThat(new N0019_RemoveNthNodeFromEndOfList().removeNthFromEnd(new ListNode(1, new ListNode(2)), 1), new ListNode(1));
        assertThat(new N0019_RemoveNthNodeFromEndOfList().removeNthFromEnd(new ListNode(1, new ListNode(2)), 2), new ListNode(2));
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = 0;
        ListNode next1 = head;
        ListNode reversed = null;
        while (next1 != null) {
            reversed = new ListNode(next1.val, reversed);
            next1 = next1.next;
            size++;
        }
        if (n > size) {
            return null;
        }

        int i = 1;
        ListNode next = reversed;
        ListNode left = null;
        ListNode right = null;
        while (next != null) {
            if (i == n - 1) {
                left = next;
            }
            if (i == n + 1) {
                right = next;
                break;
            }
            next = next.next;
            i++;
        }
        if (left != null) {
            left.next = right;
        }

        ListNode next2 = left != null ? reversed : right;
        ListNode reversed2 = null;
        while (next2 != null) {
            reversed2 = new ListNode(next2.val, reversed2);
            next2 = next2.next;
        }
        return reversed2;
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
