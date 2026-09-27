class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int removed = 0;
        int end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                // Overlap → remove this interval
                removed++;
            } else {
                // No overlap → keep this interval
                end = intervals[i][1];
            }
        }

        return removed;
    }
}