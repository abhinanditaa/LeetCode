class Solution {
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int MOD = 1000000007;

        int[][] dp = new int[m][n];
        dp[startRow][startColumn] = 1;

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        long result = 0;

        for (int move = 1; move <= maxMove; move++) {
            int[][] next = new int[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[i][j] == 0) {
                        continue;
                    }

                    for (int[] dir : directions) {
                        int x = i + dir[0];
                        int y = j + dir[1];

                        if (x < 0 || x >= m || y < 0 || y >= n) {
                            result = (result + dp[i][j]) % MOD;
                        } else {
                            next[x][y] = (int) ((next[x][y] + (long) dp[i][j]) % MOD);
                        }
                    }
                }
            }

            dp = next;
        }

        return (int) result;
    }
}