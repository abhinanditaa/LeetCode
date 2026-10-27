class Solution {
    private int[][][] dp;

    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        dp = new int[n][n][n];
        return solve(boxes, 0, n - 1, 0);
    }

    private int solve(int[] boxes, int l, int r, int k) {
        if (l > r) {
            return 0;
        }

        if (dp[l][r][k] != 0) {
            return dp[l][r][k];
        }

        int right = r;
        int count = k;

        while (right > l && boxes[right] == boxes[right - 1]) {
            right--;
            count++;
        }

        int result = solve(boxes, l, right - 1, 0)
                   + (count + 1) * (count + 1);

        for (int i = l; i < right; i++) {
            if (boxes[i] == boxes[right]) {
                result = Math.max(
                    result,
                    solve(boxes, l, i, count + 1)
                    + solve(boxes, i + 1, right - 1, 0)
                );
            }
        }

        dp[l][r][k] = result;
        return result;
    }
}