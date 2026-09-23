class Solution {
    private static final int MOD = 1337;

    public int superPow(int a, int[] b) {
        a %= MOD;

        int result = 1;

        for (int digit : b) {
            result = modPow(result, 10);
            result = (result * modPow(a, digit)) % MOD;
        }

        return result;
    }

    private int modPow(int base, int exp) {
        long result = 1;
        long b = base;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * b) % MOD;
            }

            b = (b * b) % MOD;
            exp >>= 1;
        }

        return (int) result;
    }
}