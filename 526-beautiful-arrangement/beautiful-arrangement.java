class Solution {
    private int n;
    private int[] dp;

    public int countArrangement(int n) {
        this.n = n;
        dp = new int[1 << n];
        dp[(1 << n) - 1] = 1;

        return solve(0);
    }

    private int solve(int mask) {
        if (dp[mask] != 0) {
            return dp[mask];
        }

        int pos = Integer.bitCount(mask) + 1;
        int count = 0;

        for (int num = 1; num <= n; num++) {
            int bit = 1 << (num - 1);

            if ((mask & bit) == 0 &&
                (num % pos == 0 || pos % num == 0)) {
                
                count += solve(mask | bit);
            }
        }

        return dp[mask] = count;
    }
}