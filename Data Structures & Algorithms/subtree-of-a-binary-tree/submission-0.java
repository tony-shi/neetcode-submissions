/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {  
    // 一看就是递归。但是还似乎有一点点复杂。
    // 逻辑上，有两个。
    // 那么就是说，比较这个是否是完全相等？
    // 根和根是否相等？然后如果相等，那么左右都相等，才能判断相等。
    // 理解了。所以OK。
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root==null && subRoot==null) return true;
        if(root==null) return false;
        if(subRoot==null) return true;

        // 两者都不为null。
        // 根就是当前节点。
        if(isEqualTree(root,subRoot)) return true;
        // 不是这个。
        if(isSubtree(root.left,subRoot) || isSubtree(root.right,subRoot)){
            return true;
        }
        return false;
    }

    public boolean isEqualTree(TreeNode a, TreeNode b){
        if(a==null && b==null) return true;
        if(a==null || b ==null) return false;
        if(a.val == b.val){
            return isEqualTree(a.left,b.left) && isEqualTree(a.right,b.right);
        }else{
            return false;
        }
    }
}
