class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 2][n + 2];

        // length = size of current range
        for (int len = 2; len <= n; len++) {
            for (int left = 1; left + len - 1 <= n; left++) {
                int right = left + len - 1;
                dp[left][right] = Integer.MAX_VALUE;

                // Try every possible guess
                for (int guess = left; guess <= right; guess++) {
                    int cost = guess
                            + Math.max(
                                dp[left][guess - 1],
                                dp[guess + 1][right]
                              );

                    dp[left][right] = Math.min(dp[left][right], cost);
                }
            }
        }

        return dp[1][n];
    }
}