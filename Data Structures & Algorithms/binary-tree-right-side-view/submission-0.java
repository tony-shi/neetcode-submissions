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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if(root==null) return result;
        
        // 初始化。
        LinkedList<TreeNode> currentLevel = new LinkedList<>();
        currentLevel.add(root);
        while(currentLevel.size()!=0){
            LinkedList<TreeNode> nextLevel = new LinkedList<>();
            // OK，开始就是处理了。注意，要记录最后一个元素。因此，你得set位置点。
            int index  = 0;
            int lastIndex = currentLevel.size()-1;
            for(TreeNode node : currentLevel){
                if(index == lastIndex){
                    // 是最后一个了，我们的目标。
                    result.add(node.val);
                }
                if(node.left!=null) nextLevel.add(node.left);
                if(node.right!=null) nextLevel.add(node.right);
                
                index++;
            }
            currentLevel = nextLevel;
        }
        return result;
    }
}
