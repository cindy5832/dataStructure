package com.datastructure.demo.linkedList;

public class LeetCode83 {
    // Method 1
    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p1 = head;
        ListNode p2;
        while ((p2 = p1.next) != null) {
            if (p1.val == p2.val) {
                p1.next = p2.next;
            } else {
                p1 = p1.next;
            }
        }
        return head;
    }

    // Method 2
    public ListNode deleteDuplicates2(ListNode p) {
        if (p == null || p.next == null) return p;
        if (p.val == p.next.val) {
            return deleteDuplicates2(p.next);
        } else {
            p.next = deleteDuplicates2(p.next);
            return p;
        }
    }

}
