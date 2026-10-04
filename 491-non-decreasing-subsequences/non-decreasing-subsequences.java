import java.util.*;

class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, int start,
                           List<Integer> current,
                           List<List<Integer>> result) {

        if (current.size() >= 2) {
            result.add(new ArrayList<>(current));
        }

        // Avoid duplicate choices at this recursion level
        Set<Integer> used = new HashSet<>();

        for (int i = start; i < nums.length; i++) {

            // Skip duplicate values at the same level
            if (used.contains(nums[i])) {
                continue;
            }

            // Must maintain non-decreasing order
            if (!current.isEmpty() && nums[i] < current.get(current.size() - 1)) {
                continue;
            }

            used.add(nums[i]);
            current.add(nums[i]);

            backtrack(nums, i + 1, current, result);

            current.remove(current.size() - 1);
        }
    }
}