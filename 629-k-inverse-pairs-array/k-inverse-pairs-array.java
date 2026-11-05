class Solution {
    public int kInversePairs(int n, int k) {
        final int MOD = 1000000007;

        int[] dp = new int[k + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            int[] curr = new int[k + 1];
            long sum = 0;

            for (int j = 0; j <= k; j++) {
                sum += dp[j];

                if (j >= i) {
                    sum -= dp[j - i];
                }

                sum = (sum + MOD) % MOD;
                curr[j] = (int) sum;
            }

            dp = curr;
        }

        return dp[k];
    }
}