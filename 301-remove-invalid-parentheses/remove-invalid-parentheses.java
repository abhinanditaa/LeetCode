import java.util.*;

class Solution {
    private Set<String> result = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        int leftRemove = 0;
        int rightRemove = 0;

        // Find the minimum number of '(' and ')' to remove.
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRemove++;
            } else if (c == ')') {
                if (leftRemove > 0) {
                    leftRemove--;
                } else {
                    rightRemove++;
                }
            }
        }

        dfs(s, 0, 0, leftRemove, rightRemove, new StringBuilder());

        return new ArrayList<>(result);
    }

    private void dfs(String s, int index, int balance,
                     int leftRemove, int rightRemove,
                     StringBuilder current) {

        if (balance < 0 || leftRemove < 0 || rightRemove < 0) {
            return;
        }

        if (index == s.length()) {
            if (balance == 0 && leftRemove == 0 && rightRemove == 0) {
                result.add(current.toString());
            }
            return;
        }

        char c = s.charAt(index);

        // Option 1: Remove this parenthesis.
        if (c == '(' && leftRemove > 0) {
            dfs(s, index + 1, balance,
                leftRemove - 1, rightRemove, current);
        }

        if (c == ')' && rightRemove > 0) {
            dfs(s, index + 1, balance,
                leftRemove, rightRemove - 1, current);
        }

        // Option 2: Keep this character.
        current.append(c);

        if (c == '(') {
            dfs(s, index + 1, balance + 1,
                leftRemove, rightRemove, current);
        } else if (c == ')') {
            dfs(s, index + 1, balance - 1,
                leftRemove, rightRemove, current);
        } else {
            dfs(s, index + 1, balance,
                leftRemove, rightRemove, current);
        }

        current.deleteCharAt(current.length() - 1);
    }
}