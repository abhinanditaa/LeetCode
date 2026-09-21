class Solution {

    static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        int wordIndex = -1;
        java.util.List<Integer> palindromeList = new java.util.ArrayList<>();
    }

    private TrieNode root = new TrieNode();

    public java.util.List<java.util.List<Integer>> palindromePairs(String[] words) {

        for (int i = 0; i < words.length; i++) {
            insert(words[i], i);
        }

        java.util.List<java.util.List<Integer>> result =
                new java.util.ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            search(words[i], i, result);
        }

        return result;
    }

    private void insert(String word, int index) {
        TrieNode node = root;

        for (int i = word.length() - 1; i >= 0; i--) {

            if (isPalindrome(word, 0, i)) {
                node.palindromeList.add(index);
            }

            int c = word.charAt(i) - 'a';

            if (node.next[c] == null) {
                node.next[c] = new TrieNode();
            }

            node = node.next[c];
        }

        node.palindromeList.add(index);
        node.wordIndex = index;
    }

    private void search(
            String word,
            int index,
            java.util.List<java.util.List<Integer>> result) {

        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {

            if (node.wordIndex != -1
                    && node.wordIndex != index
                    && isPalindrome(word, i, word.length() - 1)) {

                java.util.List<Integer> pair =
                        new java.util.ArrayList<>();

                pair.add(index);
                pair.add(node.wordIndex);

                result.add(pair);
            }

            int c = word.charAt(i) - 'a';

            if (node.next[c] == null) {
                return;
            }

            node = node.next[c];
        }

        for (int j : node.palindromeList) {

            if (j != index) {
                java.util.List<Integer> pair =
                        new java.util.ArrayList<>();

                pair.add(index);
                pair.add(j);

                result.add(pair);
            }
        }
    }

    private boolean isPalindrome(
            String s,
            int left,
            int right) {

        while (left < right) {

            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}