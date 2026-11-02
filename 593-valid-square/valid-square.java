import java.util.*;

class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] p = {p1, p2, p3, p4};

        int[] dist = new int[6];
        int k = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                int dx = p[i][0] - p[j][0];
                int dy = p[i][1] - p[j][1];

                dist[k++] = dx * dx + dy * dy;
            }
        }

        Arrays.sort(dist);

        return dist[0] > 0 &&
               dist[0] == dist[1] &&
               dist[1] == dist[2] &&
               dist[2] == dist[3] &&
               dist[4] == dist[5] &&
               dist[4] == 2 * dist[0];
    }
}