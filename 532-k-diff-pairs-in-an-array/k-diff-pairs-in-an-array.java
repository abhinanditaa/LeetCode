
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }

        Set<Integer> seen = new HashSet<>();
        Set<Integer> pairs = new HashSet<>();

        for (int num : nums) {
            if (seen.contains(num + k)) {
                pairs.add(num);
            }
            if (seen.contains(num - k)) {
                pairs.add(num - k);
            }
            seen.add(num);
        }

        return pairs.size();
    }
}
