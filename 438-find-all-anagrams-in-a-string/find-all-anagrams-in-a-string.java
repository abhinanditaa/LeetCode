class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s.length() < p.length()) {
            return result;
        }

        int[] count = new int[26];

        // Frequency of characters in p
        for (char c : p.toCharArray()) {
            count[c - 'a']++;
        }

        int left = 0;
        int right = 0;
        int remaining = p.length();

        while (right < s.length()) {
            char c = s.charAt(right);

            // Character is needed by p
            if (count[c - 'a'] > 0) {
                remaining--;
            }

            count[c - 'a']--;
            right++;

            // Keep window size <= p.length()
            if (right - left > p.length()) {
                char removed = s.charAt(left);

                count[removed - 'a']++;

                if (count[removed - 'a'] > 0) {
                    remaining++;
                }

                left++;
            }

            // Window contains exactly the required characters
            if (remaining == 0) {
                result.add(left);
            }
        }

        return result;
    }
}