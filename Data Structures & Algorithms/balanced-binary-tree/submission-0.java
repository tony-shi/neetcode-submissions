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
public boolean isBalanced(TreeNode root) {
        int height = maxHeight(root);
        if(height==-1){
            return false;
        }else{
            return true;
        }

    }


    public int maxHeight(TreeNode root){
        if(root==null) return 0;
        int left = maxHeight(root.left);
        int right = maxHeight(root.right);
        if(left == -1 || right == -1){
            return -1;
        }

        if(Math.abs(left-right) > 1){
            // 不平衡了。
            return -1;
        }else{
            return Math.max(left,right) +1;
        }
    }
}
