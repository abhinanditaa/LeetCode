import java.util.*;

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Use the smaller dimension for the pair of boundaries
        if (m > n) {
            return solve(matrix, n, m, k, true);
        } else {
            return solve(matrix, m, n, k, false);
        }
    }

    private int solve(int[][] matrix, int small, int large,
                      int k, boolean transpose) {

        int answer = Integer.MIN_VALUE;

        // Fix the top boundary
        for (int top = 0; top < small; top++) {

            int[] sums = new int[large];

            // Fix the bottom boundary
            for (int bottom = top; bottom < small; bottom++) {

                // Compress rows between top and bottom
                for (int col = 0; col < large; col++) {
                    if (transpose) {
                        sums[col] += matrix[col][bottom];
                    } else {
                        sums[col] += matrix[bottom][col];
                    }
                }

                // Find maximum subarray sum <= k
                answer = Math.max(answer, maxSubarrayNoLargerThanK(sums, k));

                // We cannot do better than k
                if (answer == k) {
                    return answer;
                }
            }
        }

        return answer;
    }

    private int maxSubarrayNoLargerThanK(int[] nums, int k) {
        TreeSet<Integer> prefix = new TreeSet<>();
        prefix.add(0);

        int currentSum = 0;
        int best = Integer.MIN_VALUE;

        for (int num : nums) {
            currentSum += num;

            // Need previous prefix >= currentSum - k
            Integer previous = prefix.ceiling(currentSum - k);

            if (previous != null) {
                best = Math.max(best, currentSum - previous);
            }

            prefix.add(currentSum);
        }

        return best;
    }
}