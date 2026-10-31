class Solution {
    public int arrayNesting(int[] nums) {
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            int j = i;

            while (nums[j] != -1) {
                int next = nums[j];
                nums[j] = -1;
                j = next;
                count++;
            }

            maxLength = Math.max(maxLength, count);
        }

        return maxLength;
    }
}