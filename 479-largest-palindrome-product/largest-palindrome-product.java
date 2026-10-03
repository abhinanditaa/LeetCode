class Solution {
    public int largestPalindrome(int n) {
        if (n == 1) return 9;

        int upper = (int) Math.pow(10, n) - 1;
        int lower = (int) Math.pow(10, n - 1);

        // Generate the first half of the palindrome
        int half = upper;

        while (half >= lower) {
            long palindrome = makePalindrome(half);

            // Only need to check factors up to sqrt(palindrome)
            for (int x = upper; (long) x * x >= palindrome; x--) {
                if (palindrome % x == 0) {
                    int y = (int) (palindrome / x);

                    if (y >= lower && y <= upper) {
                        return (int) (palindrome % 1337);
                    }
                }
            }

            half--;
        }

        return 0;
    }

    private long makePalindrome(int half) {
        long result = half;
        int x = half;

        while (x > 0) {
            result = result * 10 + x % 10;
            x /= 10;
        }

        return result;
    }
}