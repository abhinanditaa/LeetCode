class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int h = getHeight(root);

        // Perfect binary tree
        if (h == 0) {
            return 1;
        }

        int lastLevelNodes = findLastLevel(root, h);

        return ((1 << h) - 1) + lastLevelNodes;
    }

    // Height of the tree excluding the root level
    private int getHeight(TreeNode node) {
        int h = 0;

        while (node.left != null) {
            h++;
            node = node.left;
        }

        return h;
    }

    // Count nodes existing on the last level
    private int findLastLevel(TreeNode root, int h) {
        int low = 0;
        int high = (1 << h) - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (exists(root, h, mid)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    // Checks whether the node at index idx exists on the last level
    private boolean exists(TreeNode root, int h, int idx) {
        int left = 0;
        int right = (1 << h) - 1;

        for (int level = 0; level < h; level++) {
            int mid = left + (right - left) / 2;

            if (idx <= mid) {
                root = root.left;
                right = mid;
            } else {
                root = root.right;
                left = mid + 1;
            }
        }

        return root != null;
    }
}