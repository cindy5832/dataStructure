package com.datastructure.demo.linkedList;

public class LeetCode203 {
    // Method 1
    public static ListNode removeElement0s(ListNode head, int val) {
        ListNode s = new ListNode(-1, head); // 哨兵
        ListNode p1 = s;
        ListNode p2 = s.next;
        while (p2 != null) {
            if (p2.val == val) {
                p1.next = p2.next;
                p2 = p2.next;
                // 同 p2 = p1.next;
            } else {
                p1 = p1.next;
                p2 = p2.next;
                // 同 p2 = p1.next;
            }
        }
        return s.next;
    }

    // 修改0版
    public ListNode removeElements1(ListNode head, int val) {
        ListNode s = new ListNode(-1, head); // 哨兵
        ListNode p1 = s;
        ListNode p2;
        while ((p2 = p1.next) != null) {
            if (p2.val == val) {
                p1.next = p2.next;
            } else {
                p1 = p1.next;
            }
        }
        return s.next;
    }

    // Method 2
    public ListNode removeElements2(ListNode p, int val) {
        if (p == null) return null;
        if (p.val == val) return removeElements2(p.next, val);
        else {
            p.next = removeElements2(p.next, val);
            return p;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(6, new ListNode(3, new ListNode(6, null)))));
        ListNode n = removeElement0s(head, 6);
        System.out.println(n);
    }
}
