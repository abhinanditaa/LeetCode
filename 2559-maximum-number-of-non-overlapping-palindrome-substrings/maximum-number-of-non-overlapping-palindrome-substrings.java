class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();

        // dp[i] = maximum number of valid palindromes
        // using the first i characters.
        int[] dp = new int[n + 1];

        // pal[l] represents whether s[l...r] is a palindrome
        // for the current right endpoint r.
        boolean[] pal = new boolean[n];

        for (int r = 0; r < n; r++) {
            // We can always skip s[r].
            dp[r + 1] = dp[r];

            // Ascending l is important:
            // pal[l + 1] must still represent s[l+1...r-1].
            for (int l = 0; l <= r; l++) {
                pal[l] = s.charAt(l) == s.charAt(r)
                        && (r - l <= 1 || pal[l + 1]);

                if (pal[l] && r - l + 1 >= k) {
                    dp[r + 1] = Math.max(dp[r + 1], dp[l] + 1);
                }
            }
        }

        return dp[n];
    }
}