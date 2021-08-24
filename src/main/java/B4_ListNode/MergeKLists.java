package B4_ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 合并K个排序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 */
public class MergeKLists {

    static class SingleListNode {
        public int val;
        public SingleListNode next;
        public SingleListNode(int x) { val = x; }
    }

    public SingleListNode mergeKLists(SingleListNode[] lists) {
        Queue<SingleListNode> q = new PriorityQueue<>(new Comparator<SingleListNode>(){
            @Override
            public int compare(SingleListNode o1, SingleListNode o2) {
                return o1.val-o2.val;
            }
        });
        //把每个链表的头结点放入优先队列
        for (SingleListNode node : lists) {
            if (node != null) {
                q.add(node);
            }
        }
        SingleListNode dummy = new SingleListNode(0);
        SingleListNode curr = dummy;
        while (!q.isEmpty()) {
            curr.next = q.poll();
            curr = curr.next;
            if (curr.next != null) {
                q.add(curr.next);
            }
        }
        return dummy.next;
    }
}
