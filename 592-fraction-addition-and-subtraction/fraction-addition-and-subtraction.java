class Solution {
    public String fractionAddition(String expression) {
        int numerator = 0;
        int denominator = 1;

        int i = 0;
        int n = expression.length();

        while (i < n) {
            int sign = 1;

            if (expression.charAt(i) == '-') {
                sign = -1;
                i++;
            } else if (expression.charAt(i) == '+') {
                i++;
            }

            int num = 0;

            while (i < n && Character.isDigit(expression.charAt(i))) {
                num = num * 10 + (expression.charAt(i) - '0');
                i++;
            }

            i++; // Skip '/'

            int den = 0;

            while (i < n && Character.isDigit(expression.charAt(i))) {
                den = den * 10 + (expression.charAt(i) - '0');
                i++;
            }

            num *= sign;

            // Add fractions
            numerator = numerator * den + num * denominator;
            denominator = denominator * den;

            // Simplify using GCD
            int gcd = gcd(Math.abs(numerator), denominator);

            numerator /= gcd;
            denominator /= gcd;
        }

        return numerator + "/" + denominator;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}