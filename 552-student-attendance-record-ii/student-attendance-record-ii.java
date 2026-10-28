class Solution {
    public int checkRecord(int n) {
        final int MOD = 1_000_000_007;

        long[][] dp = new long[2][3];

        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            long[][] next = new long[2][3];

            for (int a = 0; a < 2; a++) {
                for (int l = 0; l < 3; l++) {
                    long ways = dp[a][l];

                    if (ways == 0) {
                        continue;
                    }

                    // Add P: present
                    next[a][0] = (next[a][0] + ways) % MOD;

                    // Add A: absent (at most one absence)
                    if (a == 0) {
                        next[1][0] = (next[1][0] + ways) % MOD;
                    }

                    // Add L: late (at most two consecutive lates)
                    if (l < 2) {
                        next[a][l + 1] = (next[a][l + 1] + ways) % MOD;
                    }
                }
            }

            dp = next;
        }

        long result = 0;

        for (int a = 0; a < 2; a++) {
            for (int l = 0; l < 3; l++) {
                result = (result + dp[a][l]) % MOD;
            }
        }

        return (int) result;
    }
}