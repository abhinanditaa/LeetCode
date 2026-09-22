class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] freq = new int[1001];

        // Count frequencies in nums1
        for (int num : nums1) {
            freq[num]++;
        }

        // Result can never be larger than the smaller array
        int[] result = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;

        // Match elements from nums2
        for (int num : nums2) {
            if (freq[num] > 0) {
                result[index++] = num;
                freq[num]--;
            }
        }

        return java.util.Arrays.copyOf(result, index);
    }
}