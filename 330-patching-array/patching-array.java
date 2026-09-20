class Solution {
    public int minPatches(int[] nums, int n) {
        long reach = 0;
        int i = 0;
        int patches = 0;

        while (reach < n) {
            if (i < nums.length && nums[i] <= reach + 1) {
                // nums[i] extends [1, reach]
                reach += nums[i];
                i++;
            } else {
                // Patch reach + 1
                reach += reach + 1;
                patches++;
            }
        }

        return patches;
    }
}