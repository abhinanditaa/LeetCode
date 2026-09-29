class Solution {
    public int compress(char[] chars) {
        int write = 0;
        int read = 0;

        while (read < chars.length) {
            char ch = chars[read];
            int start = read;

            // Find the end of the current group.
            while (read < chars.length && chars[read] == ch) {
                read++;
            }

            int count = read - start;

            // Write the character.
            chars[write++] = ch;

            // Write count only if greater than 1.
            if (count > 1) {
                char[] digits = String.valueOf(count).toCharArray();

                for (char digit : digits) {
                    chars[write++] = digit;
                }
            }
        }

        return write;
    }
}