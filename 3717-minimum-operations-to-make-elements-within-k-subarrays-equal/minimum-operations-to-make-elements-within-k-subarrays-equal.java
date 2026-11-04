
import java.util.Arrays;

class Solution {

    class Fenwick {
        int n;
        long[] count, sum;

        Fenwick(int n) {
            this.n = n;
            count = new long[n + 1];
            sum = new long[n + 1];
        }

        void add(int i, long c, long s) {
            while (i <= n) {
                count[i] += c;
                sum[i] += s;
                i += i & -i;
            }
        }

        long queryCount(int i) {
            long res = 0;
            while (i > 0) {
                res += count[i];
                i -= i & -i;
            }
            return res;
        }

        long querySum(int i) {
            long res = 0;
            while (i > 0) {
                res += sum[i];
                i -= i & -i;
            }
            return res;
        }

        int kth(long k) {
            int idx = 0;

            for (int bit = Integer.highestOneBit(n);
                 bit > 0; bit >>= 1) {

                int next = idx + bit;

                if (next <= n && count[next] < k) {
                    idx = next;
                    k -= count[next];
                }
            }

            return idx + 1;
        }
    }

    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] < target)
                l = mid + 1;
            else
                r = mid;
        }

        return l;
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int m = 0;

        for (int v : sorted) {
            if (m == 0 || sorted[m - 1] != v) {
                sorted[m++] = v;
            }
        }

        int[] values = Arrays.copyOf(sorted, m);

        // Calculate cost of every window of size x
        long[] cost = new long[n - x + 1];

        Fenwick bit = new Fenwick(m);

        long windowSum = 0;

        for (int i = 0; i < n; i++) {

            int pos = lowerBound(values, nums[i]) + 1;

            bit.add(pos, 1, nums[i]);
            windowSum += nums[i];

            // Remove element outside the window
            if (i >= x) {
                int oldPos = lowerBound(values, nums[i - x]) + 1;

                bit.add(oldPos, -1, -nums[i - x]);
                windowSum -= nums[i - x];
            }

            if (i >= x - 1) {

                // Find median
                int medianPos = bit.kth((x + 1L) / 2);
                long median = values[medianPos - 1];

                // Elements <= median
                long leftCount = bit.queryCount(medianPos);
                long leftSum = bit.querySum(medianPos);

                // Elements > median
                long rightCount = x - leftCount;
                long rightSum = windowSum - leftSum;

                // Minimum operations to make window equal
                cost[i - x + 1] =
                    median * leftCount - leftSum
                    + rightSum - median * rightCount;
            }
        }

        // Dynamic Programming
        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(dp[j], INF);
        }

        // dp[0][i] = 0 for all i

        for (int i = 1; i <= n; i++) {

            int maxJ = Math.min(k, i / x);

            for (int j = 1; j <= maxJ; j++) {

                // Do not select a window ending at i - 1
                dp[j][i] = dp[j][i - 1];

                // Select the last window of size x
                long previous = dp[j - 1][i - x];

                if (previous != INF) {
                    dp[j][i] = Math.min(
                        dp[j][i],
                        previous + cost[i - x]
                    );
                }
            }
        }

        return dp[k][n];
    }
}
