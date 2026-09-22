class Solution {
    int n, k;
    int[][] product;
    int[][] prefix;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;

        product = new int[4 * n][];
        prefix = new int[4 * n][];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Persistent update for subsequent queries
            update(1, 0, n - 1, index, value);

            Node res = query(1, 0, n - 1, start, n - 1);

            ans[q] = res.prefix[x];
        }

        return ans;
    }

    // Helper class for a queried/merged segment
    static class Node {
        int product;
        int[] prefix;

        Node(int k) {
            prefix = new int[k];
        }
    }

    private void build(int node, int l, int r, int[] nums) {
        product[node] = new int[k];
        prefix[node] = new int[k];

        if (l == r) {
            int rem = nums[l] % k;

            product[node][0] = 0; // unused
            product[node][rem] = 1;

            prefix[node][rem] = 1;
            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        mergeInto(node, node * 2, node * 2 + 1);
    }

    private void update(int node, int l, int r, int index, int value) {
        if (l == r) {
            java.util.Arrays.fill(product[node], 0);
            java.util.Arrays.fill(prefix[node], 0);

            int rem = value % k;

            product[node][rem] = 1;
            prefix[node][rem] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        mergeInto(node, node * 2, node * 2 + 1);
    }

    private Node query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            Node res = new Node(k);

            // Get product of entire segment
            for (int i = 0; i < k; i++) {
                if (product[node][i] != 0) {
                    res.product = i;
                    break;
                }
            }

            System.arraycopy(prefix[node], 0, res.prefix, 0, k);

            return res;
        }

        int mid = l + (r - l) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    private Node merge(Node a, Node b) {
        Node c = new Node(k);

        /*
         * Product of complete segment.
         */
        c.product = (a.product * b.product) % k;

        /*
         * Prefixes of A remain prefixes.
         */
        for (int r = 0; r < k; r++) {
            c.prefix[r] += a.prefix[r];
        }

        /*
         * A prefix that extends into B must contain
         * ALL of A followed by a prefix of B.
         *
         * product = product(A) * product(prefix(B))
         */
        for (int r = 0; r < k; r++) {
            if (b.prefix[r] == 0) {
                continue;
            }

            int rem = (a.product * r) % k;
            c.prefix[rem] += b.prefix[r];
        }

        return c;
    }

    private void mergeInto(int parent, int left, int right) {
        java.util.Arrays.fill(product[parent], 0);
        java.util.Arrays.fill(prefix[parent], 0);

        /*
         * Product of the complete segment.
         */
        int leftProduct = 0;
        int rightProduct = 0;

        for (int r = 0; r < k; r++) {
            if (product[left][r] != 0) {
                leftProduct = r;
            }

            if (product[right][r] != 0) {
                rightProduct = r;
            }
        }

        int totalProduct = (leftProduct * rightProduct) % k;
        product[parent][totalProduct] = 1;

        /*
         * Prefixes entirely inside the left child.
         */
        for (int r = 0; r < k; r++) {
            prefix[parent][r] += prefix[left][r];
        }

        /*
         * Prefixes extending into the right child.
         */
        for (int r = 0; r < k; r++) {
            if (prefix[right][r] == 0) {
                continue;
            }

            int rem = (leftProduct * r) % k;
            prefix[parent][rem] += prefix[right][r];
        }
    }
}