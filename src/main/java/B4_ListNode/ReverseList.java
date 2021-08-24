package B4_ListNode;

/**
 * 反转单链表
 *
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class ReverseList {
    static class SingleListNode {
        public int val;
        public SingleListNode next;
        public SingleListNode(int x) { val = x; }
    }

    public SingleListNode reverseList1(SingleListNode head) {
        //递归
        if (head == null || head.next == null) {
            return head;
        }
        SingleListNode newHead = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public SingleListNode reverseList2(SingleListNode head) {
         //迭代
         SingleListNode prev = null;
         SingleListNode curr = head;
         while (curr != null) {
             SingleListNode next = curr.next;
             curr.next = prev;
             prev = curr;
             curr = next;
         }
         return prev;
    }
}
