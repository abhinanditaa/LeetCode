import java.util.*;

class Solution {
    public int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size();
        int n = forest.get(0).size();

        List<int[]> trees = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int h = forest.get(i).get(j);

                if (h > 1) {
                    trees.add(new int[]{h, i, j});
                }
            }
        }

        Collections.sort(trees, (a, b) -> Integer.compare(a[0], b[0]));

        int total = 0;
        int sr = 0, sc = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int[] tree : trees) {
            int tr = tree[1];
            int tc = tree[2];

            int[][] dist = new int[m][n];
            for (int[] row : dist) {
                Arrays.fill(row, -1);
            }

            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{sr, sc});
            dist[sr][sc] = 0;

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];

                if (r == tr && c == tc) {
                    break;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr >= 0 && nr < m && nc >= 0 && nc < n
                            && forest.get(nr).get(nc) > 0
                            && dist[nr][nc] == -1) {

                        dist[nr][nc] = dist[r][c] + 1;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }

            if (dist[tr][tc] == -1) {
                return -1;
            }

            total += dist[tr][tc];

            sr = tr;
            sc = tc;

            forest.get(tr).set(tc, 1);
        }

        return total;
    }
}