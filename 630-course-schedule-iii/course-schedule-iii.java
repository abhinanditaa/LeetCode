import java.util.*;

class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int total = 0;

        for (int[] course : courses) {
            int duration = course[0];
            int lastDay = course[1];

            total += duration;
            pq.offer(duration);

            if (total > lastDay) {
                total -= pq.poll();
            }
        }

        return pq.size();
    }
}