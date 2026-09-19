import java.util.*;

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }

        List<Integer>[] graph = new ArrayList[n];
        int[] degree = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);

            degree[u]++;
            degree[v]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();

        // Add all initial leaves.
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }

        int remaining = n;

        while (remaining > 2) {
            int size = queue.size();
            remaining -= size;

            for (int i = 0; i < size; i++) {
                int leaf = queue.poll();

                for (int neighbor : graph[leaf]) {
                    degree[neighbor]--;

                    if (degree[neighbor] == 1) {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return new ArrayList<>(queue);
    }
}