package com.datastructure.demo.linkedList;

public class LeetCode142 {
    public ListNode detectCycle(ListNode head) {
        ListNode r = head;
        ListNode t = head;
        while (r != null && r.next != null) {
            t = t.next;
            r = r.next.next;
            if (r == t) {
                t = head;
                while (true) {
                    if (t == r) return t;
                    t = t.next;
                    r = r.next;
                }
            }
        }
        return null;
    }
}
