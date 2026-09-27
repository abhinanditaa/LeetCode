class Solution {
    private int count = 0;
    private long target;

    public int pathSum(TreeNode root, int targetSum) {
        target = targetSum;

        HashMap<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1);

        dfs(root, 0L, prefix);

        return count;
    }

    private void dfs(TreeNode node, long currentSum,
                     HashMap<Long, Integer> prefix) {

        if (node == null) {
            return;
        }

        currentSum += node.val;

        // Number of previous prefix sums that make
        // the current path sum equal to target
        count += prefix.getOrDefault(currentSum - target, 0);

        // Add current prefix sum
        prefix.put(currentSum,
                   prefix.getOrDefault(currentSum, 0) + 1);

        dfs(node.left, currentSum, prefix);
        dfs(node.right, currentSum, prefix);

        // Backtrack: remove current path's prefix sum
        prefix.put(currentSum, prefix.get(currentSum) - 1);
    }
}