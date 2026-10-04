class Solution {
    public String smallestGoodBase(String n) {
        long num = Long.parseLong(n);

        // Maximum possible digits is 60 because n <= 10^18
        for (int m = 60; m >= 2; m--) {

            long left = 2;
            long right = num - 1;

            while (left <= right) {
                long base = left + (right - left) / 2;

                int result = compare(num, base, m);

                if (result == 0) {
                    return String.valueOf(base);
                } else if (result < 0) {
                    // 1 + base + base^2 + ... < num
                    left = base + 1;
                } else {
                    // Sum > num
                    right = base - 1;
                }
            }
        }

        // Every n has representation 11 in base n - 1
        return String.valueOf(num - 1);
    }

    private int compare(long n, long base, int m) {
        long sum = 1;
        long power = 1;

        for (int i = 1; i < m; i++) {

            // Prevent power * base from overflowing
            if (power > (n - 1) / base) {
                return 1;
            }

            power *= base;
            sum += power;

            if (sum > n) {
                return 1;
            }
        }

        if (sum == n) {
            return 0;
        }

        return -1;
    }
}