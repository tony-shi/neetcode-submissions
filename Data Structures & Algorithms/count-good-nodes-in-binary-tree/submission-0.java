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
    public int goodNodes(TreeNode root) {
        return dfs(root,Integer.MIN_VALUE);
        
    }

    // 逻辑上，一个dfs的先序遍历。

    // 特别的，遍历的时候，要传递给下一个层级，当前的路径上的，最大的值。
    // 以这个最大值来进行分析。

    public int dfs(TreeNode current,int preMax){
        int totalCount = 0;
        if(current==null) return 0;
        if(current.val >= preMax ){
            preMax = current.val;
            totalCount++;
            totalCount = totalCount +  dfs(current.left,preMax);
            totalCount = totalCount +  dfs(current.right,preMax);
            return totalCount;
        }else{
            // 当前节点不是，前面的preMax大于了。
            totalCount = totalCount +  dfs(current.left,preMax);
            totalCount = totalCount +  dfs(current.right,preMax);
            return totalCount;

        }
    }

}
