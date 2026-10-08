import java.util.*;

class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;

        if (n == 1) {
            return 0;
        }

        // Store all indices having the same value.
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        boolean[] visited = new boolean[n];
        int[] queue = new int[n];

        int front = 0;
        int rear = 0;

        queue[rear++] = 0;
        visited[0] = true;

        int steps = 0;

        while (front < rear) {
            int size = rear - front;

            while (size-- > 0) {
                int i = queue[front++];

                if (i == n - 1) {
                    return steps;
                }

                // Jump to i - 1
                if (i > 0 && !visited[i - 1]) {
                    visited[i - 1] = true;
                    queue[rear++] = i - 1;
                }

                // Jump to i + 1
                if (i + 1 < n && !visited[i + 1]) {
                    visited[i + 1] = true;
                    queue[rear++] = i + 1;
                }

                // Jump to every index having the same value.
                List<Integer> sameValue = map.get(arr[i]);

                if (sameValue != null) {
                    for (int j : sameValue) {
                        if (!visited[j]) {
                            visited[j] = true;
                            queue[rear++] = j;
                        }
                    }

                    // Critical optimization:
                    // Never process this value's indices again.
                    map.remove(arr[i]);
                }
            }

            steps++;
        }

        return -1;
    }
}