import java.util.HashMap;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> lastIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Integer previous = lastIndex.put(nums[i], i);

            if (previous != null && i - previous <= k) {
                return true;
            }
        }

        return false;
    }
}