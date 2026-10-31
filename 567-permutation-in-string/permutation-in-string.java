class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        if (n > m) {
            return false;
        }

        int[] count = new int[26];

        for (int i = 0; i < n; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }

        int matches = 0;

        for (int i = 0; i < 26; i++) {
            if (count[i] == 0) {
                matches++;
            }
        }

        if (matches == 26) {
            return true;
        }

        for (int i = n; i < m; i++) {
            int right = s2.charAt(i) - 'a';
            int left = s2.charAt(i - n) - 'a';

            if (count[right] == 0) {
                matches--;
            }
            count[right]--;

            if (count[right] == 0) {
                matches++;
            }

            if (count[left] == 0) {
                matches--;
            }
            count[left]++;

            if (count[left] == 0) {
                matches++;
            }

            if (matches == 26) {
                return true;
            }
        }

        return false;
    }
}