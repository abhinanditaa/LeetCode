class Solution {
    public int[] singleNumber(int[] nums) {
        int xor = 0;

        // XOR of the two unique numbers
        for (int num : nums) {
            xor ^= num;
        }

        // Get any set bit where the two numbers differ
        int bit = xor & -xor;

        int a = 0;
        int b = 0;

        // Divide numbers into two groups
        for (int num : nums) {
            if ((num & bit) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }

        return new int[]{a, b};
    }
}