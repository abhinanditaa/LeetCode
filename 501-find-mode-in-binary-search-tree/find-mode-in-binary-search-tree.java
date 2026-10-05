class Solution {
    private int prev = 0;
    private int count = 0;
    private int maxCount = 0;
    private boolean first = true;
    private java.util.List<Integer> result = new java.util.ArrayList<>();

    public int[] findMode(TreeNode root) {
        inorder(root);

        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }

    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);

        if (first || node.val != prev) {
            count = 1;
            first = false;
        } else {
            count++;
        }

        if (count > maxCount) {
            result.clear();
            result.add(node.val);
            maxCount = count;
        } else if (count == maxCount) {
            result.add(node.val);
        }

        prev = node.val;

        inorder(node.right);
    }
}