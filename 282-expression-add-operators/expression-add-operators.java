class Solution {

    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();

        backtrack(
            num,
            target,
            0,
            0L,
            0L,
            new StringBuilder(),
            result
        );

        return result;
    }

    private void backtrack(
        String num,
        long target,
        int index,
        long value,
        long previous,
        StringBuilder expression,
        List<String> result
    ) {
        // All digits have been used.
        if (index == num.length()) {
            if (value == target) {
                result.add(expression.toString());
            }
            return;
        }

        int length = expression.length();

        long number = 0;

        for (int i = index; i < num.length(); i++) {

            // No leading zeros.
            if (i > index && num.charAt(index) == '0') {
                break;
            }

            number = number * 10 + (num.charAt(i) - '0');

            String current = num.substring(index, i + 1);

            // First number: no operator before it.
            if (index == 0) {
                expression.append(current);

                backtrack(
                    num,
                    target,
                    i + 1,
                    number,
                    number,
                    expression,
                    result
                );

                expression.setLength(length);
            } else {

                // Addition
                expression.append('+').append(current);

                backtrack(
                    num,
                    target,
                    i + 1,
                    value + number,
                    number,
                    expression,
                    result
                );

                expression.setLength(length);

                // Subtraction
                expression.append('-').append(current);

                backtrack(
                    num,
                    target,
                    i + 1,
                    value - number,
                    -number,
                    expression,
                    result
                );

                expression.setLength(length);

                // Multiplication
                expression.append('*').append(current);

                backtrack(
                    num,
                    target,
                    i + 1,
                    value - previous + previous * number,
                    previous * number,
                    expression,
                    result
                );

                expression.setLength(length);
            }
        }
    }
}