import java.util.*;

class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        int answer = 0;

        for (int i = 0; i < n; i++) {
            Map<Long, Integer> map = new HashMap<>();

            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                long dx = points[i][0] - points[j][0];
                long dy = points[i][1] - points[j][1];

                long dist = dx * dx + dy * dy;

                int count = map.getOrDefault(dist, 0);

                // New point can pair with all previous
                // points having the same distance.
                answer += 2 * count;

                map.put(dist, count + 1);
            }
        }

        return answer;
    }
}