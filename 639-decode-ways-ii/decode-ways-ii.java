class Solution {
    private static final long MOD = 1000000007;

    public int numDecodings(String s) {
        long prev2 = 1;
        long prev1 = ways1(s.charAt(0));

        for (int i = 1; i < s.length(); i++) {
            char a = s.charAt(i - 1);
            char b = s.charAt(i);

            long curr = (ways1(b) * prev1 +
                         ways2(a, b) * prev2) % MOD;

            prev2 = prev1;
            prev1 = curr;
        }

        return (int) prev1;
    }

    private long ways1(char c) {
        if (c == '*') return 9;
        if (c == '0') return 0;
        return 1;
    }

    private long ways2(char a, char b) {
        if (a == '*' && b == '*') return 15;

        if (a == '*') {
            return (b >= '0' && b <= '6') ? 2 : 1;
        }

        if (b == '*') {
            if (a == '1') return 9;
            if (a == '2') return 6;
            return 0;
        }

        int num = (a - '0') * 10 + (b - '0');

        return (num >= 10 && num <= 26) ? 1 : 0;
    }
}