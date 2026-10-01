class Solution {
    private int max;
    private int target;
    private Boolean[] memo;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        max = maxChoosableInteger;
        target = desiredTotal;

        if (target <= 0) {
            return true;
        }

        int sum = max * (max + 1) / 2;

        if (sum < target) {
            return false;
        }

        memo = new Boolean[1 << max];

        return dfs(0, 0);
    }

    private boolean dfs(int mask, int current) {
        if (memo[mask] != null) {
            return memo[mask];
        }

        for (int i = 1; i <= max; i++) {
            int bit = 1 << (i - 1);

            // Number i is already used
            if ((mask & bit) != 0) {
                continue;
            }

            // We win immediately
            if (current + i >= target) {
                return memo[mask] = true;
            }

            // If opponent cannot win from the next state, we can win
            if (!dfs(mask | bit, current + i)) {
                return memo[mask] = true;
            }
        }

        return memo[mask] = false;
    }
}