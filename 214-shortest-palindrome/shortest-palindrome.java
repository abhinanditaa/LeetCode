class Solution {
    public String shortestPalindrome(String s) {
        int n = s.length();

        if (n <= 1) {
            return s;
        }

        // Create: s + "#" + reverse(s)
        String rev = new StringBuilder(s).reverse().toString();
        String combined = s + "#" + rev;

        // KMP LPS array
        int[] lps = new int[combined.length()];

        for (int i = 1, len = 0; i < combined.length(); ) {
            if (combined.charAt(i) == combined.charAt(len)) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                i++;
            }
        }

        // Length of the longest palindromic prefix
        int palindromeLength = lps[combined.length() - 1];

        // Characters after the palindromic prefix
        String suffix = s.substring(palindromeLength);

        // Reverse suffix and put it in front
        return new StringBuilder(suffix).reverse().append(s).toString();
    }
}