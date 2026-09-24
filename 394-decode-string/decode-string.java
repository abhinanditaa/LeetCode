import java.util.*;

class Solution {
    public String decodeString(String s) {
        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<StringBuilder> stringStack = new ArrayDeque<>();

        StringBuilder current = new StringBuilder();
        int number = 0;

        for (char c : s.toCharArray()) {

            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } 
            else if (c == '[') {
                countStack.push(number);
                stringStack.push(current);

                number = 0;
                current = new StringBuilder();
            } 
            else if (c == ']') {
                int repeat = countStack.pop();
                StringBuilder previous = stringStack.pop();

                for (int i = 0; i < repeat; i++) {
                    previous.append(current);
                }

                current = previous;
            } 
            else {
                current.append(c);
            }
        }

        return current.toString();
    }
}