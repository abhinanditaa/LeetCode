import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0); // start
            a[i][1] = intervals.get(i).get(1); // end
            a[i][2] = intervals.get(i).get(2); // weight
            a[i][3] = i;                       // original index
        }

        // Sort by start time
        Arrays.sort(a, (x, y) -> {
            if (x[0] != y[0]) {
                return Integer.compare(x[0], y[0]);
            }
            return Integer.compare(x[3], y[3]);
        });

        // next[i] = first interval with start > a[i][1]
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int lo = i + 1;
            int hi = n;

            while (lo < hi) {
                int mid = (lo + hi) >>> 1;

                if (a[mid][0] > a[i][1]) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            next[i] = lo;
        }

        /*
         * dp[k][i]:
         * Best result using at most k intervals
         * from i to n-1.
         */
        State[][] dp = new State[5][n + 1];

        // Using 0 intervals always gives score 0.
        for (int i = 0; i <= n; i++) {
            dp[0][i] = new State(0, new int[0]);
        }

        // No intervals remaining.
        for (int k = 1; k <= 4; k++) {
            dp[k][n] = new State(0, new int[0]);
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {

                // Option 1: skip current interval
                State skip = dp[k][i + 1];

                // Option 2: take current interval
                State future = dp[k - 1][next[i]];

                int[] ids = new int[future.ids.length + 1];
                ids[0] = a[i][3];

                System.arraycopy(
                    future.ids,
                    0,
                    ids,
                    1,
                    future.ids.length
                );

                // Required output is sorted by original index
                Arrays.sort(ids);

                State take = new State(
                    future.score + a[i][2],
                    ids
                );

                dp[k][i] = better(take, skip);
            }
        }

        return dp[4][0].ids;
    }

    private State better(State a, State b) {
        // Maximum score first
        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        // Lexicographically smallest indices
        int len = Math.min(a.ids.length, b.ids.length);

        for (int i = 0; i < len; i++) {
            if (a.ids[i] != b.ids[i]) {
                return a.ids[i] < b.ids[i] ? a : b;
            }
        }

        // If one is a prefix of the other,
        // shorter array is lexicographically smaller.
        return a.ids.length <= b.ids.length ? a : b;
    }

    static class State {
        long score;
        int[] ids;

        State(long score, int[] ids) {
            this.score = score;
            this.ids = ids;
        }
    }
}