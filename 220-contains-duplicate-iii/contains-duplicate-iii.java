import java.util.TreeSet;

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> window = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            long num = nums[i];

            // Find the smallest value >= num - valueDiff
            Long candidate = window.ceiling(num - (long) valueDiff);

            // If candidate <= num + valueDiff, valid pair exists
            if (candidate != null && candidate <= num + (long) valueDiff) {
                return true;
            }

            window.add(num);

            // Keep only the last indexDiff elements
            if (i >= indexDiff) {
                window.remove((long) nums[i - indexDiff]);
            }
        }

        return false;
    }
}