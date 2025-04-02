package com.datastructure.demo.linkedList;

public class LeetCode19 {
    // method 1
    private int recursion(ListNode p, int n) {
        if (p == null) return 0;
        int nth = recursion(p.next, n);
        System.out.println(p.val + ":" + nth);
        if(nth == n) p.next = p.next.next;
        return nth + 1;
    }

    public ListNode removeNthFromEnd(ListNode head, int n){
        ListNode s = new ListNode(-1, head);
        recursion(s, n);
        return s.next;
    }

    // method 2
    public ListNode removeNthFromEnd1(ListNode head , int n){
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2 = s;
        for(int i = 0; i < n+1; i++){
            p2 = p2.next;
        }
        while(p2 != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = p1.next.next;
        return s.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        System.out.println(head);
        System.out.println(new LeetCode19().removeNthFromEnd(head, 5));
    }
}
