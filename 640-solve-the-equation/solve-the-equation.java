class Solution {
    public String solveEquation(String equation) {
        int x = 0, num = 0;
        int sign = 1;
        int side = 1;
        int i = 0;

        while (i < equation.length()) {
            char c = equation.charAt(i);

            if (c == '=') {
                side = -1;
                sign = 1;
                i++;
                continue;
            }

            if (c == '+' || c == '-') {
                sign = (c == '+') ? 1 : -1;
                i++;
                continue;
            }

            int value = 0;
            boolean hasNumber = false;

            while (i < equation.length() && Character.isDigit(equation.charAt(i))) {
                value = value * 10 + equation.charAt(i) - '0';
                hasNumber = true;
                i++;
            }

            if (i < equation.length() && equation.charAt(i) == 'x') {
                x += side * sign * (hasNumber ? value : 1);
                i++;
            } else {
                num += side * sign * value;
            }
        }

        if (x == 0) {
            return num == 0 ? "Infinite solutions" : "No solution";
        }

        return "x=" + (-num / x);
    }
}