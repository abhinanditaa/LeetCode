import java.util.*;

class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.size(); i++) {
            int val = nums.get(i).get(0);
            pq.offer(new int[]{val, i, 0});
            max = Math.max(max, val);
        }

        int start = 0, end = Integer.MAX_VALUE;

        while (pq.size() == nums.size()) {
            int[] curr = pq.poll();

            int min = curr[0];

            if (max - min < end - start) {
                start = min;
                end = max;
            }

            int list = curr[1];
            int index = curr[2] + 1;

            if (index == nums.get(list).size()) {
                break;
            }

            int next = nums.get(list).get(index);

            pq.offer(new int[]{next, list, index});
            max = Math.max(max, next);
        }

        return new int[]{start, end};
    }
}