class Solution {
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder result = new StringBuilder();
        int count = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (c == '-') {
                continue;
            }

            if (count == k) {
                result.append('-');
                count = 0;
            }

            result.append(Character.toUpperCase(c));
            count++;
        }

        return result.reverse().toString();
    }
}