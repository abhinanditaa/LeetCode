class Solution {
    public String nearestPalindromic(String n) {
        int len = n.length();
        long num = Long.parseLong(n);

        Set<Long> candidates = new HashSet<>();

        // Boundary candidates
        candidates.add((long) Math.pow(10, len - 1) - 1);
        candidates.add((long) Math.pow(10, len) + 1);

        // Take the first half
        int halfLen = (len + 1) / 2;
        long prefix = Long.parseLong(n.substring(0, halfLen));

        // Generate palindromes using prefix - 1, prefix, prefix + 1
        for (long p = prefix - 1; p <= prefix + 1; p++) {
            if (p < 0) continue;

            String left = String.valueOf(p);
            StringBuilder sb = new StringBuilder(left);

            String right = (len % 2 == 0)
                    ? new StringBuilder(left).reverse().toString()
                    : new StringBuilder(left.substring(0, left.length() - 1))
                            .reverse().toString();

            sb.append(right);

            candidates.add(Long.parseLong(sb.toString()));
        }

        // Find closest palindrome
        long answer = -1;
        long minDiff = Long.MAX_VALUE;

        for (long candidate : candidates) {
            if (candidate == num) continue;

            long diff = Math.abs(candidate - num);

            if (diff < minDiff || (diff == minDiff && candidate < answer)) {
                minDiff = diff;
                answer = candidate;
            }
        }

        return String.valueOf(answer);
    }
}