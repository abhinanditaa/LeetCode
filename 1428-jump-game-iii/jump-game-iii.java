class Solution {
    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        int[] queue = new int[arr.length];

        int front = 0;
        int rear = 0;

        queue[rear++] = start;
        visited[start] = true;

        while (front < rear) {
            int i = queue[front++];

            if (arr[i] == 0) {
                return true;
            }

            int right = i + arr[i];
            int left = i - arr[i];

            if (right < arr.length && !visited[right]) {
                visited[right] = true;
                queue[rear++] = right;
            }

            if (left >= 0 && !visited[left]) {
                visited[left] = true;
                queue[rear++] = left;
            }
        }

        return false;
    }
}