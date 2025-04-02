package com.datastructure.demo.linkedList;

public class LeetCode141 {
    public boolean hasCycle(ListNode head){
        ListNode r = head;
        ListNode t = head;
        while (r != null && r.next != null){
            t = t.next;
            r = r.next.next;
            if(r == t){
                return true;
            }
        }
        return false;
    }
}
