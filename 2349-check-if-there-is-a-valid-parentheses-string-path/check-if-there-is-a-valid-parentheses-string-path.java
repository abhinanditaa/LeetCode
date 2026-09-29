class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // A valid path must have even length.
        if ((m + n - 1) % 2 != 0) return false;

        // The first character must be '(' and the last must be ')'.
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        /*
         * Maximum possible balance = path length.
         * We use a bitset:
         * bit k = 1 means balance k is possible.
         */
        int maxLen = m + n;
        int words = (maxLen + 63) >> 6;

        long[][] dp = new long[n][words];

        // Starting cell: balance = 1
        dp[0][0] = 1L << 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                long[] cur = dp[j];

                // Get possible balances from top and left.
                long[] top = (i > 0) ? dp[j] : null;
                long[] left = (j > 0) ? dp[j - 1] : null;

                // Merge top and left, then shift balances.
                if (left != null) {
                    for (int k = 0; k < words; k++) {
                        cur[k] |= left[k];
                    }
                }

                if (top != null) {
                    for (int k = 0; k < words; k++) {
                        cur[k] |= top[k];
                    }
                }

                // Current character changes balance.
                if (grid[i][j] == '(') {
                    shiftLeft(cur);
                } else {
                    shiftRight(cur);
                }

                // Balance can never become negative.
                clearNegativeBalance(cur);
            }
        }

        // Valid parentheses string must end with balance = 0.
        return (dp[n - 1][0] & 1L) != 0;
    }

    // Shift all possible balances by +1.
    private void shiftLeft(long[] bits) {
        long carry = 0;

        for (int i = 0; i < bits.length; i++) {
            long x = bits[i];
            bits[i] = (x << 1) | carry;
            carry = x >>> 63;
        }
    }

    // Shift all possible balances by -1.
    private void shiftRight(long[] bits) {
        long carry = 0;

        for (int i = bits.length - 1; i >= 0; i--) {
            long x = bits[i];
            bits[i] = (x >>> 1) | carry;
            carry = x << 63;
        }
    }

    // Balance < 0 is impossible and is naturally removed by right shift.
    private void clearNegativeBalance(long[] bits) {
        // Nothing needed: bit 0 is the minimum valid balance.
    }
}