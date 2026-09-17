class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = n + 1;

        // best[i] = minimum length of a valid subarray
        // completely inside indices [0, i-1]
        int[] best = new int[n + 1];
        java.util.Arrays.fill(best, INF);

        int ans = INF;
        int left = 0;
        int sum = 0;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left++];
            }

            // Carry forward the best previous subarray
            best[right + 1] = best[right];

            if (sum == target) {
                int len = right - left + 1;

                // Best subarray must end before 'left'
                if (best[left] != INF) {
                    ans = Math.min(ans, len + best[left]);
                }

                // This is the best valid subarray ending at 'right'
                best[right + 1] = Math.min(best[right + 1], len);
            }
        }

        return ans == INF ? -1 : ans;
    }
}