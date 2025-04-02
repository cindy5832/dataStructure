package com.datastructure.demo.linkedList;

// LeetCode 206
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append("[");
        ListNode p = this;
        while (p != null) {
            sb.append(p.val);
            if (p.next != null) {
                sb.append(",");
            }
            p = p.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // Method 1
    public static ListNode reverseList(ListNode o1) {
        ListNode n1 = null;
        ListNode p = o1;
        while (p != null){
            n1 = new ListNode(p.val, n1);
            p = p.next;
        }
        return n1;
    }
    // Method 2
    public static ListNode reverseList1(ListNode head){
        List list1 = new List(head);
        List list2 = new List(null);
        while (true){
            ListNode first = list1.removeFirst();
            if(first == null) break;
            list2.addFirst(first);
        }
        return list2.head;
    }
    static class List{
        ListNode head;
        public List(ListNode head){
            this.head = head;
        }
        public void addFirst(ListNode first){
            first.next = head;
            head = first;
        }
        public ListNode removeFirst(){
            ListNode first = head;
            if(first != null){
                head = first.next;
            }
            return first;
        }
    }

    // Method 3 遞規
    public static ListNode reverseList2(ListNode p){
        if(p == null || p.next == null){
            return p; // 最後節點
        }
        ListNode last = reverseList2(p.next);
        p.next.next = p;
        p.next = null;
        return last;
    }

    // Method 4
    /**
     * n1
     * o1  o2
     * 1 -> 2 -> 3 -> 4 -> 5 -> null
     * n1  o1
     * 2 -> 1 -> 3 -> 4 -> 5 -> null
     * n1       o1
     * 3 -> 2 -> 1 -> 4 -> 5 -> null
     * n1             o1
     * 4 -> 3 -> 2 -> 1 -> 5 -> null
     * n1                  o1
     * 5 -> 4 -> 3 -> 2 -> 1 -> null
     **/
    public static ListNode reverseList3(ListNode o1){
        // 1. 空鏈表 2. 1個元素
        if (o1 == null || o1.next == null) return o1;
        ListNode o2 = o1.next; // 舊的second
        ListNode n1 = o1; // 新的head
        while (o2 != null){
            o1.next = o2.next;
            o2.next = n1;
            n1 = o2;
            o2 = o1.next;
            System.out.println("o1 = " + o1);
        }
        return n1;
    }

    // Method 5
    public static ListNode reverseList4(ListNode o1){
        if(o1 == null || o1.next == null) return o1;
        ListNode n1 = null;
        while (o1 != null){
            ListNode o2 = o1.next;
            o1.next = n1;
            n1= o1;
            o1 = o2;
        }
        return n1;
    }



    public static void main(String[] args) {
        ListNode o5 = new ListNode(5, null);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        System.out.println(o1);
//        ListNode n1 = reverseList(o1);
//        ListNode n2 = reverseList1(o1);
//        ListNode n3 = reverseList2(o1);
//        ListNode n4 = reverseList3(o1);
        ListNode n5 = reverseList4(o1);
        System.out.println(n5);
    }


}
