class Solution {
    public int numberOfSets(int n, int k) {
        final int MOD = 1_000_000_007;
        int r = 2 * k;
        int total = n + k - 1;

        long[] dp = new long[r + 1];
        dp[0] = 1;

        for (int i = 1; i <= total; i++) {
            for (int j = Math.min(i, r); j >= 1; j--) {
                dp[j] = (dp[j] + dp[j - 1]) % MOD;
            }
        }

        return (int) dp[r];
    }
}