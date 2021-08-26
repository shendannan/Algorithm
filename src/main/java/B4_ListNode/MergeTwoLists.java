package B4_ListNode;

/**
 * 合并两个排序链表
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 *
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * 限制：0 <= 链表长度 <= 1000
 *
 * https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof
 */
public class MergeTwoLists {

    static class SingleListNode {
        public int val;
        public SingleListNode next;
        public SingleListNode(int x) { val = x; }
    }

    public SingleListNode mergeTwoLists(SingleListNode l1, SingleListNode l2) {
        //构造伪头结点
        SingleListNode dummy = new SingleListNode(0), cur = dummy;
        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            }else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
}
