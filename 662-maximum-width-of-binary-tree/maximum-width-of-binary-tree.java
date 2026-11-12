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
    public int widthOfBinaryTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Long> indices = new LinkedList<>();

        queue.offer(root);
        indices.offer(0L);

        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            long minIndex = indices.peek();
            long first = 0;
            long last = 0;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                long index = indices.poll() - minIndex;

                if (i == 0) {
                    first = index;
                }

                if (i == size - 1) {
                    last = index;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                    indices.offer(2 * index);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                    indices.offer(2 * index + 1);
                }
            }

            maxWidth = Math.max(maxWidth, (int)(last - first + 1));
        }

        return maxWidth;
    }
}