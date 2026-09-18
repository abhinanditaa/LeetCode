class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        java.util.Arrays.fill(first, n);
        java.util.Arrays.fill(last, -1);

        // Find first and last occurrence of every character.
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        // Store valid intervals [start, end].
        int[][] intervals = new int[26][2];
        int count = 0;

        /*
         * For every character, expand its interval.
         * If we encounter a character whose first occurrence
         * is before the current start, this interval is invalid.
         */
        for (int c = 0; c < 26; c++) {
            if (first[c] == n) {
                continue;
            }

            int l = first[c];
            int r = last[c];
            boolean valid = true;

            for (int i = l; i <= r; i++) {
                int x = s.charAt(i) - 'a';

                if (first[x] < l) {
                    valid = false;
                    break;
                }

                r = Math.max(r, last[x]);
            }

            if (valid) {
                intervals[count][0] = l;
                intervals[count][1] = r;
                count++;
            }
        }

        /*
         * Sort intervals by their ending position.
         * Since there are only 26 intervals, simple sorting is enough.
         */
        for (int i = 1; i < count; i++) {
            int[] cur = intervals[i];
            int j = i - 1;

            while (j >= 0 && intervals[j][1] > cur[1]) {
                intervals[j + 1] = intervals[j];
                j--;
            }

            intervals[j + 1] = cur;
        }

        /*
         * Greedy:
         * Always take the valid interval with the smallest end
         * that does not overlap the previously selected interval.
         *
         * This maximizes the number of non-overlapping intervals.
         * For this problem, choosing the smallest valid interval
         * ending first also gives the minimum total length.
         */
        List<String> ans = new java.util.ArrayList<>();

        int prevEnd = -1;

        for (int i = 0; i < count; i++) {
            int l = intervals[i][0];
            int r = intervals[i][1];

            if (l > prevEnd) {
                ans.add(s.substring(l, r + 1));
                prevEnd = r;
            }
        }

        return ans;
    }
}