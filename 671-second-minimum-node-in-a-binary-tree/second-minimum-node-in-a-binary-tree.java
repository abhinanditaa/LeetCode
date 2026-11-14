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
    public int findSecondMinimumValue(TreeNode root) {
        long ans = find(root, root.val);
        return ans == Long.MAX_VALUE ? -1 : (int) ans;
    }

    private long find(TreeNode node, int min) {
        if (node == null) {
            return Long.MAX_VALUE;
        }

        if (node.val > min) {
            return node.val;
        }

        return Math.min(
            find(node.left, min),
            find(node.right, min)
        );
    }
}
