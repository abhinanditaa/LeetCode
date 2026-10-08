class Solution {
    public int minMoves(int[] nums, int limit) {
        int[] diff = new int[2 * limit + 2];
        int n = nums.length;

        for (int i = 0; i < n / 2; i++) {
            int a = nums[i];
            int b = nums[n - 1 - i];

            int low = Math.min(a, b);
            int high = Math.max(a, b);
            int sum = a + b;

            // Initially: 2 moves for every possible sum [2, 2*limit]
            diff[2] += 2;
            diff[2 * limit + 1] -= 2;

            // 1 move for [low + 1, high + limit]
            diff[low + 1]--;
            diff[high + limit + 1]++;

            // 0 moves for exactly [sum]
            diff[sum]--;
            diff[sum + 1]++;
        }

        int moves = 0;
        int answer = n;

        for (int sum = 2; sum <= 2 * limit; sum++) {
            moves += diff[sum];
            answer = Math.min(answer, moves);
        }

        return answer;
    }
}