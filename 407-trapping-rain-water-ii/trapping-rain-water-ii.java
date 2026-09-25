import java.util.*;

class Solution {

    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;

        if (m <= 2 || n <= 2) {
            return 0;
        }

        PriorityQueue<Cell> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.height, b.height)
        );

        boolean[][] visited = new boolean[m][n];

        // Add all boundary cells
        for (int i = 0; i < m; i++) {
            addCell(i, 0, heightMap, visited, pq);
            addCell(i, n - 1, heightMap, visited, pq);
        }

        for (int j = 1; j < n - 1; j++) {
            addCell(0, j, heightMap, visited, pq);
            addCell(m - 1, j, heightMap, visited, pq);
        }

        int water = 0;

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        while (!pq.isEmpty()) {
            Cell current = pq.poll();

            for (int[] dir : directions) {
                int nr = current.row + dir[0];
                int nc = current.col + dir[1];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n ||
                    visited[nr][nc]) {
                    continue;
                }

                visited[nr][nc] = true;

                int nextHeight = heightMap[nr][nc];

                // Water trapped at this cell
                if (nextHeight < current.height) {
                    water += current.height - nextHeight;
                }

                // Effective boundary height
                int boundaryHeight = Math.max(current.height, nextHeight);

                pq.offer(new Cell(nr, nc, boundaryHeight));
            }
        }

        return water;
    }

    private void addCell(int row, int col,
                         int[][] heightMap,
                         boolean[][] visited,
                         PriorityQueue<Cell> pq) {

        if (!visited[row][col]) {
            visited[row][col] = true;
            pq.offer(new Cell(row, col, heightMap[row][col]));
        }
    }

    static class Cell {
        int row;
        int col;
        int height;

        Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }
}