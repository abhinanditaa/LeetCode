class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;

        // count[i] = number of papers with exactly i citations
        // Any citation > n can be treated as n.
        int[] count = new int[n + 1];

        for (int citation : citations) {
            count[Math.min(citation, n)]++;
        }

        int papers = 0;

        // Try h from n down to 0.
        for (int h = n; h >= 0; h--) {
            papers += count[h];

            if (papers >= h) {
                return h;
            }
        }

        return 0;
    }
}