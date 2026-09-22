import java.util.*;

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        // Width ascending, height descending for equal widths
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(b[1], a[1]);
        });

        // tails[i] = smallest possible ending height
        // of an increasing subsequence of length i + 1
        int[] tails = new int[envelopes.length];
        int size = 0;

        for (int[] envelope : envelopes) {
            int height = envelope[1];

            int left = 0;
            int right = size;

            // Lower bound: first position >= height
            while (left < right) {
                int mid = left + (right - left) / 2;

                if (tails[mid] < height) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            tails[left] = height;

            if (left == size) {
                size++;
            }
        }

        return size;
    }
}