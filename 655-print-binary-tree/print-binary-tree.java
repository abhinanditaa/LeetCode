class Solution {
    public List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root);

        int m = height + 1;
        int n = (1 << (height + 1)) - 1;

        List<List<String>> res = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add("");
            }
            res.add(row);
        }

        fill(root, res, 0, 0, n - 1);

        return res;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return -1;
        }

        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    private void fill(TreeNode root, List<List<String>> res,
                      int row, int left, int right) {
        if (root == null) {
            return;
        }

        int mid = (left + right) / 2;

        res.get(row).set(mid, String.valueOf(root.val));

        fill(root.left, res, row + 1, left, mid - 1);
        fill(root.right, res, row + 1, mid + 1, right);
    }
}