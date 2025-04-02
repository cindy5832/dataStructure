package com.datastructure.demo.linkedList;

public class LeetCode234 {
    /* Step1 找中間節點
     * Step2 中間點後反轉
     * Step3 反轉後鍊表逐一比對
     */
    public boolean isPalindrome(ListNode head){
        ListNode middle = middle(head);
        ListNode n1 = reverse(middle);
        while(n1 != null){
            if(n1.val != head.val) return false;
            n1 = n1.next;
            head = head.next;
        }
        return true;
    }

    private ListNode middle(ListNode head){
        ListNode p1 = head;
        ListNode p2 = head;
        while(p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return p1;
    }

    private ListNode reverse(ListNode o1){
        ListNode n1 = null;
        while (o1 != null){
            ListNode o2 = o1.next;
            o1.next = n1;
            n1 = o1;
            o1 = o2;
        }
        return n1;
    }

    /* Step1 找中間點的同時反轉前半個鍊表
     * Step2 反轉後半個鍊表 與 中間點開始的後半個鍊表逐一比較
     */
    public static boolean isPalindrome1(ListNode head){
        ListNode p1 = head;
        ListNode p2 = head;
        ListNode n1 = null;
        ListNode o1 = head;
        while(p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            // 反轉代碼
//            ListNode o2 = o1.next; // p1
            o1.next = n1;
            n1 = o1;
            o1 = p1;
        }
        System.out.println("p1="+p1.next);
        System.out.println("n1="+n1);
        if(p2 != null) p1 = p1.next; // 奇數節點
        while (n1 != null){
            if(n1.val != p1.val){
                return false;
            }
            n1 = n1.next;
            p1 = p1.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(2, new ListNode(1, null)))));
        System.out.println(isPalindrome1(head));
    }

}
