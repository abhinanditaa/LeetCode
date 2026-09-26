class Solution {
    public int strongPasswordChecker(String password) {
        int n = password.length();

        boolean lower = false;
        boolean upper = false;
        boolean digit = false;

        for (char c : password.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                lower = true;
            } else if (c >= 'A' && c <= 'Z') {
                upper = true;
            } else if (c >= '0' && c <= '9') {
                digit = true;
            }
        }

        int missing = 0;
        if (!lower) missing++;
        if (!upper) missing++;
        if (!digit) missing++;

        // Too short
        if (n < 6) {
            return Math.max(missing, 6 - n);
        }

        // Find repeating groups
        int replace = 0;
        int[] mod = new int[3];

        for (int i = 0; i < n; ) {
            int j = i;

            while (j < n && password.charAt(j) == password.charAt(i)) {
                j++;
            }

            int len = j - i;

            if (len >= 3) {
                replace += len / 3;
                mod[len % 3]++;
            }

            i = j;
        }

        // Already within valid length
        if (n <= 20) {
            return Math.max(missing, replace);
        }

        // Too long: use deletions to reduce replacements.
        int delete = n - 20;

        // First delete from groups where one deletion reduces
        // the number of replacements by 1.
        int use = Math.min(delete, mod[0]);
        replace -= use;
        delete -= use;

        // Groups where two deletions reduce replacements by 1.
        use = Math.min(delete, mod[1] * 2);
        replace -= use / 2;
        delete -= use;

        // Remaining deletions: every 3 deletions reduce one replacement.
        replace -= delete / 3;

        return (n - 20) + Math.max(missing, replace);
    }
}