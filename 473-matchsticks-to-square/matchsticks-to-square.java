class Solution {
    public boolean makesquare(int[] matchsticks) {
        int n = matchsticks.length;

        if (n < 4) return false;

        int total = 0;
        for (int x : matchsticks) {
            total += x;
        }

        if (total % 4 != 0) return false;

        int side = total / 4;

        // Larger sticks first -> much faster pruning
        java.util.Arrays.sort(matchsticks);

        if (matchsticks[n - 1] > side) return false;

        int[] sides = new int[4];

        return backtrack(matchsticks, n - 1, sides, side);
    }

    private boolean backtrack(int[] sticks, int index, int[] sides, int target) {
        if (index < 0) {
            return sides[0] == target &&
                   sides[1] == target &&
                   sides[2] == target &&
                   sides[3] == target;
        }

        int stick = sticks[index];

        for (int i = 0; i < 4; i++) {

            // Stick cannot fit on this side
            if (sides[i] + stick > target) continue;

            // Avoid trying identical side states
            if (i > 0 && sides[i] == sides[i - 1]) continue;

            sides[i] += stick;

            if (backtrack(sticks, index - 1, sides, target)) {
                return true;
            }

            sides[i] -= stick;

            // If this stick could not be placed on an empty side,
            // there is no need to try other empty sides.
            if (sides[i] == 0) break;
        }

        return false;
    }
}