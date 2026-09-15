import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();

        // Quick impossibility checks
        if (n < k * (k + 1) / 2 ||
            n > k * (19 - k) / 2) {
            return result;
        }

        backtrack(1, k, n, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int k, int target,
                           List<Integer> current,
                           List<List<Integer>> result) {

        if (k == 0) {
            if (target == 0) {
                result.add(new ArrayList<>(current));
            }
            return;
        }

        // Not enough numbers remaining
        if (10 - start < k) {
            return;
        }

        for (int num = start; num <= 9; num++) {
            // Since numbers are increasing, further numbers
            // will only make the sum larger.
            if (num > target) {
                break;
            }

            current.add(num);

            backtrack(
                num + 1,
                k - 1,
                target - num,
                current,
                result
            );

            current.remove(current.size() - 1);
        }
    }
}