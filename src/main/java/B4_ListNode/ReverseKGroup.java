package B4_ListNode;

/**
 * K个一组翻转链表
 * 给你一个链表，每k个节点一组进行翻转，返回翻转后的链表。
 * k是一个正整数，它的值小于或等于链表的长度，最后不足k个的节点保持原有顺序。
 * <p>
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 */
public class ReverseKGroup {
    static class SingleListNode {
        public int val;
        public SingleListNode next;
        public SingleListNode(int x) { val = x; }
    }

    //递归法
    public SingleListNode reverseKGroup(SingleListNode head, int k) {
        //退出递归条件
        if (head == null || k < 2) return head;
        SingleListNode cur = head;
        int count = 1;
        //截取K个
        while (cur != null && count < k) {
            count++;
            cur = cur.next;
        }
        if (cur == null) return head;
        SingleListNode other = cur.next;
        SingleListNode prev = reverseKGroup(other, k);
        SingleListNode temp = head;
        SingleListNode next;
        while (temp != other) {
            next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
        }
        return cur;
    }

    //迭代法
    public SingleListNode reverseKGroup1(SingleListNode head, int k) {
        SingleListNode dummy = new SingleListNode(0);
        dummy.next = head;
        SingleListNode pre = dummy;

        while (head != null) {
            SingleListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k，不足k个直接返回
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return dummy.next;
                }
            }
            SingleListNode next = tail.next;
            SingleListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = next;
            pre = tail;
            head = tail.next;
        }

        return dummy.next;
    }

    public SingleListNode[] myReverse(SingleListNode head, SingleListNode tail) {
        SingleListNode prev = tail.next;
        SingleListNode curr = head;
        while (prev != tail) {
            SingleListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return new SingleListNode[]{tail, head};
    }

}
