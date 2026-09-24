import java.util.*;

class Solution {
    public NestedInteger deserialize(String s) {
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }

        Deque<NestedInteger> stack = new ArrayDeque<>();
        NestedInteger current = null;
        int num = 0;
        int sign = 1;
        boolean readingNumber = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '[') {
                stack.push(new NestedInteger());
            } 
            else if (c == '-' ) {
                sign = -1;
            } 
            else if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
                readingNumber = true;
            } 
            else if (c == ',' || c == ']') {
                if (readingNumber) {
                    stack.peek().add(new NestedInteger(sign * num));
                    num = 0;
                    sign = 1;
                    readingNumber = false;
                }

                if (c == ']' && stack.size() > 1) {
                    current = stack.pop();
                    stack.peek().add(current);
                }
            }
        }

        return stack.pop();
    }
}