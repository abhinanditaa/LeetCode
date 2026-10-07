class Solution {
    public String findLongestWord(String s, java.util.List<String> dictionary) {
        String ans = "";

        for (String word : dictionary) {
            if (isSubsequence(word, s)) {
                if (word.length() > ans.length() ||
                    (word.length() == ans.length() && word.compareTo(ans) < 0)) {
                    ans = word;
                }
            }
        }

        return ans;
    }

    private boolean isSubsequence(String word, String s) {
        int i = 0;

        for (int j = 0; j < s.length() && i < word.length(); j++) {
            if (word.charAt(i) == s.charAt(j)) {
                i++;
            }
        }

        return i == word.length();
    }
}