import java.util.*;

class Solution {
    public List<List<Integer>> kSmallestPairs(
            int[] nums1, int[] nums2, int k) {

        List<List<Integer>> result = new ArrayList<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(
                (long) nums1[a[0]] + nums2[a[1]],
                (long) nums1[b[0]] + nums2[b[1]]
            )
        );

        // Start with the first k elements of nums1 paired
        // with the first element of nums2.
        int limit = Math.min(k, nums1.length);

        for (int i = 0; i < limit; i++) {
            pq.offer(new int[]{i, 0});
        }

        while (k-- > 0 && !pq.isEmpty()) {
            int[] current = pq.poll();

            int i = current[0];
            int j = current[1];

            result.add(Arrays.asList(nums1[i], nums2[j]));

            // Move to the next element in nums2 for this nums1[i].
            if (j + 1 < nums2.length) {
                pq.offer(new int[]{i, j + 1});
            }
        }

        return result;
    }
}