class Solution {
    private final java.util.Map<String, java.util.List<Integer>> memo = new java.util.HashMap<>();

    public java.util.List<Integer> diffWaysToCompute(String expression) {
        if (memo.containsKey(expression)) {
            return memo.get(expression);
        }

        java.util.List<Integer> result = new java.util.ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {
            char op = expression.charAt(i);

            if (op == '+' || op == '-' || op == '*') {
                java.util.List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                java.util.List<Integer> right = diffWaysToCompute(expression.substring(i + 1));

                for (int a : left) {
                    for (int b : right) {
                        if (op == '+') {
                            result.add(a + b);
                        } else if (op == '-') {
                            result.add(a - b);
                        } else {
                            result.add(a * b);
                        }
                    }
                }
            }
        }

        // Expression contains only a number
        if (result.isEmpty()) {
            result.add(Integer.parseInt(expression));
        }

        memo.put(expression, result);
        return result;
    }
}