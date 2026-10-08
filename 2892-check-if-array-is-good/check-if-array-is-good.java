class Solution {
    public boolean isGood(int[] nums) {
        int n = nums.length - 1;
        int[] count = new int[n + 1];

        for (int num : nums) {
            if (num > n) {
                return false;
            }

            count[num]++;

            if (count[num] > 2) {
                return false;
            }
        }

        for (int i = 1; i < n; i++) {
            if (count[i] != 1) {
                return false;
            }
        }

        return count[n] == 2;
    }
}