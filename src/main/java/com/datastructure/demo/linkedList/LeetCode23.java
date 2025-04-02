package com.datastructure.demo.linkedList;

public class LeetCode23 {
    public ListNode mergeTwoLists1(ListNode p1, ListNode p2) {
        ListNode s = new ListNode(-1, null);
        ListNode p = s;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
        }
        p = p.next;
        if (p1 != null) {
            p.next = p1;
        }
        if (p2.next != null) {
            p.next = p2;
        }
        return s.next;
    }

    public ListNode mergeLists1(ListNode[] lists) {
        if (lists.length == 0) return null;
        return split(lists, 0, lists.length - 1);
    }

    // 返回合併後鍊表，i和j為左右邊界
    private ListNode split(ListNode[] lists, int i, int j) {
        if (i == j) return lists[i];// 數組內僅有一個ListNode
        int m = (1 + j) >>> 1;
        ListNode left = split(lists, i, m);
        ListNode right = split(lists, m + 1, j);
        return mergeTwoLists1(left, right);
    }
}
