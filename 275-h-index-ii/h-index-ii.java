class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0;
        int right = n - 1;

        // Find the first position where:
        // citations[mid] >= n - mid
        while (left <= right) {
            int mid = left + (right - left) / 2;

            int papers = n - mid;

            if (citations[mid] >= papers) {
                // This position may work, but we want
                // the earliest possible position.
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return n - left;
    }
}