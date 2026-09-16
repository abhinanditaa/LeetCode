class Solution {
    public int calculate(String s) {
        int n = s.length();
        int result = 0;
        int last = 0;
        int num = 0;
        char op = '+';

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            }

            if ((c < '0' || c > '9') && c != ' ' || i == n - 1) {
                switch (op) {
                    case '+':
                        result += last;
                        last = num;
                        break;

                    case '-':
                        result += last;
                        last = -num;
                        break;

                    case '*':
                        last *= num;
                        break;

                    case '/':
                        last /= num;
                        break;
                }

                op = c;
                num = 0;
            }
        }

        return result + last;
    }
}