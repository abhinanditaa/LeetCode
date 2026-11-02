import java.util.*;

class Solution {
    public boolean isValid(String code) {
        Stack<String> stack = new Stack<>();
        int n = code.length();
        int i = 0;

        while (i < n) {

            // Code must start with an opening tag
            if (i > 0 && stack.isEmpty()) {
                return false;
            }

            // Handle CDATA
            if (code.startsWith("<![CDATA[", i)) {
                if (stack.isEmpty()) return false;

                int end = code.indexOf("]]>", i + 9);

                if (end == -1) return false;

                i = end + 3;
            }

            // Handle closing tag
            else if (code.startsWith("</", i)) {
                int end = code.indexOf('>', i);

                if (end == -1) return false;

                String tag = code.substring(i + 2, end);

                if (tag.length() < 1 || tag.length() > 9) {
                    return false;
                }

                for (int j = 0; j < tag.length(); j++) {
                    if (tag.charAt(j) < 'A' || tag.charAt(j) > 'Z') {
                        return false;
                    }
                }

                if (stack.isEmpty() || !stack.pop().equals(tag)) {
                    return false;
                }

                i = end + 1;
            }

            // Handle opening tag
            else if (code.charAt(i) == '<') {
                int end = code.indexOf('>', i);

                if (end == -1) return false;

                String tag = code.substring(i + 1, end);

                if (tag.length() < 1 || tag.length() > 9) {
                    return false;
                }

                for (int j = 0; j < tag.length(); j++) {
                    if (tag.charAt(j) < 'A' || tag.charAt(j) > 'Z') {
                        return false;
                    }
                }

                stack.push(tag);
                i = end + 1;
            }

            // Regular text
            else {
                if (stack.isEmpty()) return false;
                i++;
            }
        }

        return stack.isEmpty();
    }
}