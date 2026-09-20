class Solution {
    private int[][] matrix;
    private int[][] dp;
    private int rows;
    private int cols;

    private final int[] dr = {-1, 1, 0, 0};
    private final int[] dc = {0, 0, -1, 1};

    public int longestIncreasingPath(int[][] matrix) {
        this.matrix = matrix;
        rows = matrix.length;
        cols = matrix[0].length;

        dp = new int[rows][cols];

        int answer = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                answer = Math.max(answer, dfs(r, c));
            }
        }

        return answer;
    }

    private int dfs(int r, int c) {
        if (dp[r][c] != 0) {
            return dp[r][c];
        }

        int best = 1;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr >= 0 && nr < rows &&
                nc >= 0 && nc < cols &&
                matrix[nr][nc] > matrix[r][c]) {

                best = Math.max(best, 1 + dfs(nr, nc));
            }
        }

        dp[r][c] = best;
        return best;
    }
}