class Solution {
    private Map<Integer, Integer> map = new HashMap<>();
    private int maxFreq = 0;

    public int[] findFrequentTreeSum(TreeNode root) {
        dfs(root);

        int[] result = new int[map.size()];
        int index = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxFreq) {
                result[index++] = entry.getKey();
            }
        }

        return Arrays.copyOf(result, index);
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int sum = node.val + dfs(node.left) + dfs(node.right);

        int frequency = map.getOrDefault(sum, 0) + 1;
        map.put(sum, frequency);

        maxFreq = Math.max(maxFreq, frequency);

        return sum;
    }
}