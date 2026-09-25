import java.util.*;

class Solution {
    public boolean canCross(int[] stones) {
        int n = stones.length;

        // Map: stone position -> possible jump sizes
        Map<Integer, Set<Integer>> dp = new HashMap<>();

        for (int stone : stones) {
            dp.put(stone, new HashSet<>());
        }

        // First jump must be exactly 1
        dp.get(0).add(0);

        for (int i = 0; i < n; i++) {
            int position = stones[i];

            for (int jump : dp.get(position)) {

                // Try k - 1, k, k + 1
                for (int nextJump = jump - 1;
                     nextJump <= jump + 1;
                     nextJump++) {

                    if (nextJump <= 0) {
                        continue;
                    }

                    int nextPosition = position + nextJump;

                    if (nextPosition == stones[n - 1]) {
                        return true;
                    }

                    if (dp.containsKey(nextPosition)) {
                        dp.get(nextPosition).add(nextJump);
                    }
                }
            }
        }

        return false;
    }
}