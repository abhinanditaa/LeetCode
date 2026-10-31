class Solution {
    static final int MOD = 1_000_000_007;

    int m, n;
    int[][] grid;
    int[][][] memo;

    public int uniquePaths(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        memo = new int[m][n][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j][0] = -2;
                memo[i][j][1] = -2;
            }
        }

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1 || dp[i][j] == 0) {
                    continue;
                }

                long ways = dp[i][j];

                // Move right
                int right = resolve(i, j + 1, 0);

                if (right != -1) {
                    int r = right / n;
                    int c = right % n;
                    dp[r][c] = (dp[r][c] + ways) % MOD;
                }

                // Move down
                int down = resolve(i + 1, j, 1);

                if (down != -1) {
                    int r = down / n;
                    int c = down % n;
                    dp[r][c] = (dp[r][c] + ways) % MOD;
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }

    // dir = 0 means moving right
    // dir = 1 means moving down
    private int resolve(int i, int j, int dir) {

        // Check grid boundaries
        if (i >= m || j >= n) {
            return -1;
        }

        // Return non-mirror cell
        if (grid[i][j] == 0) {
            return i * n + j;
        }

        // Use memoized result
        if (memo[i][j][dir] != -2) {
            return memo[i][j][dir];
        }

        int result;

        if (dir == 0) {
            // Right into mirror -> turn down
            result = resolve(i + 1, j, 1);
        } else {
            // Down into mirror -> turn right
            result = resolve(i, j + 1, 0);
        }

        return memo[i][j][dir] = result;
    }
}