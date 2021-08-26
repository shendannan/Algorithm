package B5_tree;

import B0_BasicDataStructure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 翻转二叉树
 * <p>
 * https://leetcode-cn.com/problems/invert-binary-tree/
 */
public class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        //bfs
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                //直接交换整个树节点，子树的位置也一起交换了
                TreeNode rightTree = cur.right;
                cur.right = cur.left;
                cur.left = rightTree;
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
        }
        return root;
    }
}
