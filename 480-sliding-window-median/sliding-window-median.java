class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] ans = new double[n - k + 1];

        java.util.TreeSet<int[]> small = new java.util.TreeSet<>(
            (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0])
                                    : Integer.compare(a[1], b[1])
        );

        java.util.TreeSet<int[]> large = new java.util.TreeSet<>(
            (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0])
                                    : Integer.compare(a[1], b[1])
        );

        for (int i = 0; i < n; i++) {
            int[] cur = {nums[i], i};

            // Add to appropriate half
            if (small.isEmpty() || compare(cur, small.last()) <= 0) {
                small.add(cur);
            } else {
                large.add(cur);
            }

            // Keep sizes balanced
            balance(small, large);

            // Remove element outside the window
            if (i >= k) {
                int[] old = {nums[i - k], i - k};

                if (!small.remove(old)) {
                    large.remove(old);
                }

                balance(small, large);
            }

            // Calculate median
            if (i >= k - 1) {
                if ((k & 1) == 1) {
                    ans[i - k + 1] = small.last()[0];
                } else {
                    ans[i - k + 1] =
                        ((long) small.last()[0] + large.first()[0]) / 2.0;
                }
            }
        }

        return ans;
    }

    private void balance(
            java.util.TreeSet<int[]> small,
            java.util.TreeSet<int[]> large) {

        // small can have at most one more element than large
        while (small.size() > large.size() + 1) {
            large.add(small.pollLast());
        }

        while (small.size() < large.size()) {
            small.add(large.pollFirst());
        }
    }

    private int compare(int[] a, int[] b) {
        if (a[0] != b[0]) {
            return Integer.compare(a[0], b[0]);
        }
        return Integer.compare(a[1], b[1]);
    }
}