package B5_tree;

import B0_BasicDataStructure.TreeNode;

/**
 * 最近公共祖先
 * 给定一个二叉搜索树BST, 找到该树中两个指定节点的最近公共祖先。
 * 一个节点也可以是它自己的祖先
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree
 */
public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //利用二叉搜索树的特点
        //如果p q都小于root，说明p q肯定在root的左子树中；
        //如果p q都大于root，说明p q肯定在root的右子树中；
        //如果一个在左一个在右 则说明此时的root记为对应的最近公共祖先
        if(p.val<root.val && q.val<root.val){
            return lowestCommonAncestor(root.left,p,q);
        }
        if(p.val>root.val && q.val>root.val){
            return lowestCommonAncestor(root.right,p,q);
        }
        return root;
    }
}
