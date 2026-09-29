class Solution {
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--; // curr = 1 is already the first number

        while (k > 0) {
            long count = countNumbers(n, curr, curr + 1);

            if (count <= k) {
                // Skip this entire prefix subtree.
                k -= count;
                curr++;
            } else {
                // Go deeper into this prefix.
                curr *= 10;
                k--;
            }
        }

        return curr;
    }

    private long countNumbers(long n, long prefix, long nextPrefix) {
        long count = 0;

        while (prefix <= n) {
            count += Math.min(n + 1, nextPrefix) - prefix;

            prefix *= 10;
            nextPrefix *= 10;
        }

        return count;
    }
}