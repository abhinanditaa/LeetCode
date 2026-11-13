
class Solution {
    public int strangePrinter(String s) {
        int n = s.length();

        // Remove consecutive duplicate characters
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if (i == 0 || s.charAt(i) != s.charAt(i - 1)) {
                sb.append(s.charAt(i));
            }
        }

        s = sb.toString();
        n = s.length();

        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;

            for (int j = i + 1; j < n; j++) {
                dp[i][j] = dp[i + 1][j] + 1;

                for (int k = i + 1; k <= j; k++) {
                    if (s.charAt(i) == s.charAt(k)) {
                        int left = (k == i + 1) ? 0 : dp[i + 1][k - 1];
                        int right = dp[k][j];

                        dp[i][j] = Math.min(dp[i][j], left + right);
                    }
                }
            }
        }

        return dp[0][n - 1];
    }
}
