import java.math.BigInteger;

class Solution {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();

        // Try every possible first number.
        for (int i = 1; i <= n - 2; i++) {
            // First number cannot have leading zero.
            if (num.charAt(0) == '0' && i > 1) {
                break;
            }

            BigInteger first = new BigInteger(num.substring(0, i));

            // Try every possible second number.
            for (int j = i + 1; j <= n - 1; j++) {
                // Second number cannot have leading zero.
                if (num.charAt(i) == '0' && j - i > 1) {
                    break;
                }

                BigInteger second = new BigInteger(num.substring(i, j));

                if (check(num, j, first, second)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean check(String num, int index,
                          BigInteger first, BigInteger second) {

        int count = 2;

        while (index < num.length()) {
            BigInteger sum = first.add(second);
            String sumStr = sum.toString();

            if (!num.startsWith(sumStr, index)) {
                return false;
            }

            index += sumStr.length();

            first = second;
            second = sum;
            count++;
        }

        return count >= 3;
    }
}