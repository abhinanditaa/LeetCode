import java.util.*;

class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        
        // dp[i] stores:
        // difference -> number of arithmetic subsequences
        // of length >= 2 ending at i
        Map<Long, Integer>[] dp = new HashMap[n];

        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }

        long answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {

                long diff = (long) nums[i] - nums[j];

                // Number of sequences already ending at j
                // with the same difference.
                int previous = dp[j].getOrDefault(diff, 0);

                // Start a new 2-element sequence [nums[j], nums[i]]
                // and extend all previous sequences.
                int current = previous + 1;

                dp[i].put(diff, dp[i].getOrDefault(diff, 0) + current);

                // Only sequences of length >= 3 count as answers.
                answer += previous;
            }
        }

        return (int) answer;
    }
}