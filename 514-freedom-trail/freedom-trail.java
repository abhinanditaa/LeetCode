class Solution {
    public int findRotateSteps(String ring, String key) {
        int n = ring.length();
        int m = key.length();

        // memo[keyIndex][ringPosition]
        int[][] memo = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }

        return solve(ring, key, 0, 0, memo);
    }

    private int solve(String ring, String key, int keyIndex,
                      int ringPos, int[][] memo) {

        if (keyIndex == key.length()) {
            return 0;
        }

        if (memo[keyIndex][ringPos] != -1) {
            return memo[keyIndex][ringPos];
        }

        int n = ring.length();
        int minSteps = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            if (ring.charAt(i) == key.charAt(keyIndex)) {

                // Clockwise distance
                int clockwise = Math.abs(i - ringPos);

                // Anticlockwise distance
                int anticlockwise = n - clockwise;

                int rotate = Math.min(clockwise, anticlockwise);

                // +1 for pressing the center button
                int total = rotate + 1
                        + solve(ring, key, keyIndex + 1, i, memo);

                minSteps = Math.min(minSteps, total);
            }
        }

        return memo[keyIndex][ringPos] = minSteps;
    }
}