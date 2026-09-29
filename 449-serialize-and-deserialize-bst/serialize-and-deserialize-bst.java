import java.util.*;

public class Codec {

    // Serialize BST using preorder traversal.
    public String serialize(TreeNode root) {
        if (root == null) return "";

        StringBuilder sb = new StringBuilder();
        serializePreorder(root, sb);

        return sb.toString().trim();
    }

    private void serializePreorder(TreeNode node, StringBuilder sb) {
        if (node == null) return;

        sb.append(node.val).append(' ');

        serializePreorder(node.left, sb);
        serializePreorder(node.right, sb);
    }

    // Deserialize preorder sequence using BST bounds.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) return null;

        String[] values = data.split(" ");
        int[] index = {0};

        return build(values, index, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode build(String[] values, int[] index, long low, long high) {
        if (index[0] >= values.length) {
            return null;
        }

        int value = Integer.parseInt(values[index[0]]);

        // This value doesn't belong in the current subtree.
        if (value <= low || value >= high) {
            return null;
        }

        index[0]++;

        TreeNode node = new TreeNode(value);

        node.left = build(values, index, low, value);
        node.right = build(values, index, value, high);

        return node;
    }
}