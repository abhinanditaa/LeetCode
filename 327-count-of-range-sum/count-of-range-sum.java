class Solution {
    private long[] temp;
    private int lower;
    private int upper;

    public int countRangeSum(int[] nums, int lower, int upper) {
        this.lower = lower;
        this.upper = upper;

        int n = nums.length;
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        temp = new long[n + 1];

        return mergeSort(prefix, 0, n + 1);
    }

    private int mergeSort(long[] prefix, int left, int right) {
        if (right - left <= 1) {
            return 0;
        }

        int mid = left + (right - left) / 2;

        int count = mergeSort(prefix, left, mid);
        count += mergeSort(prefix, mid, right);

        int low = mid;
        int high = mid;

        // Count valid pairs
        for (int i = left; i < mid; i++) {

            while (low < right &&
                   prefix[low] - prefix[i] < lower) {
                low++;
            }

            while (high < right &&
                   prefix[high] - prefix[i] <= upper) {
                high++;
            }

            count += high - low;
        }

        // Merge sorted halves
        int i = left;
        int j = mid;
        int k = left;

        while (i < mid && j < right) {
            if (prefix[i] <= prefix[j]) {
                temp[k++] = prefix[i++];
            } else {
                temp[k++] = prefix[j++];
            }
        }

        while (i < mid) {
            temp[k++] = prefix[i++];
        }

        while (j < right) {
            temp[k++] = prefix[j++];
        }

        for (i = left; i < right; i++) {
            prefix[i] = temp[i];
        }

        return count;
    }
}