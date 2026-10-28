import java.util.*;

class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Long, Integer> edges = new HashMap<>();
        int maxEdges = 0;

        for (List<Integer> row : wall) {
            long width = 0;

            for (int i = 0; i < row.size() - 1; i++) {
                width += row.get(i);

                int count = edges.getOrDefault(width, 0) + 1;
                edges.put(width, count);

                maxEdges = Math.max(maxEdges, count);
            }
        }

        return wall.size() - maxEdges;
    }
}