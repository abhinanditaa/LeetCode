class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        int[] stack = new int[n];
        int top = -1;

        // Find matching parentheses
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack[++top] = i;
            } else if (s.charAt(i) == ')') {
                int open = stack[top--];
                pair[open] = i;
                pair[i] = open;
            }
        }

        StringBuilder result = new StringBuilder();
        int direction = 1;

        // Traverse while jumping between matching parentheses
        for (int i = 0; i < n; i += direction) {
            char c = s.charAt(i);

            if (c == '(' || c == ')') {
                i = pair[i];
                direction = -direction;
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}