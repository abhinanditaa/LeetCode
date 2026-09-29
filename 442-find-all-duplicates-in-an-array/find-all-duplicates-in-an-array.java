import java.util.*;

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();

        for (int x : nums) {
            int index = Math.abs(x) - 1;

            if (nums[index] < 0) {
                result.add(Math.abs(x));
            } else {
                nums[index] = -nums[index];
            }
        }

        return result;
    }
}