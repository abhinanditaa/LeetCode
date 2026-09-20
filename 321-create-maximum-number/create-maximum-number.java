class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;

        int[] best = new int[k];

        int start = Math.max(0, k - n);
        int end = Math.min(k, m);

        for (int i = start; i <= end; i++) {
            int j = k - i;

            int[] a = maxSubsequence(nums1, i);
            int[] b = maxSubsequence(nums2, j);

            int[] candidate = merge(a, b);

            if (greater(candidate, 0, best, 0)) {
                best = candidate;
            }
        }

        return best;
    }

    // Get the maximum subsequence of length k
    private int[] maxSubsequence(int[] nums, int k) {
        int[] stack = new int[k];
        int top = 0;

        int remove = nums.length - k;

        for (int num : nums) {
            while (top > 0 && remove > 0 && stack[top - 1] < num) {
                top--;
                remove--;
            }

            if (top < k) {
                stack[top++] = num;
            } else {
                remove--;
            }
        }

        return stack;
    }

    // Merge two subsequences into the lexicographically largest sequence
    private int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < a.length || j < b.length) {
            if (greater(a, i, b, j)) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        return result;
    }

    // Returns true if a[i...] is lexicographically greater than b[j...]
    private boolean greater(int[] a, int i, int[] b, int j) {
        while (i < a.length && j < b.length) {
            if (a[i] != b[j]) {
                return a[i] > b[j];
            }

            i++;
            j++;
        }

        return i < a.length;
    }
}