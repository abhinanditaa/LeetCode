import java.util.*;

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int offset = 10001;
        int size = 20002;

        int[] tree = new int[size + 1];
        Integer[] result = new Integer[n];

        for (int i = n - 1; i >= 0; i--) {
            int index = nums[i] + offset + 1;

            // Count elements strictly smaller than nums[i].
            result[i] = query(tree, index - 1);

            // Add current number to the Fenwick Tree.
            update(tree, index);
        }

        return Arrays.asList(result);
    }

    private void update(int[] tree, int index) {
        while (index < tree.length) {
            tree[index]++;
            index += index & -index;
        }
    }

    private int query(int[] tree, int index) {
        int count = 0;

        while (index > 0) {
            count += tree[index];
            index -= index & -index;
        }

        return count;
    }
}