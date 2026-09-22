class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        boolean[] seen = new boolean[1001];
        boolean[] added = new boolean[1001];

        // Mark elements present in nums1
        for (int num : nums1) {
            seen[num] = true;
        }

        // Store unique intersection elements
        int[] temp = new int[Math.min(nums1.length, nums2.length)];
        int count = 0;

        for (int num : nums2) {
            if (seen[num] && !added[num]) {
                temp[count++] = num;
                added[num] = true;
            }
        }

        return java.util.Arrays.copyOf(temp, count);
    }
}