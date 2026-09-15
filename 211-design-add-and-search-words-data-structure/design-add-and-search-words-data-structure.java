class WordDictionary {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
    }

    private final TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }

            node = node.children[index];
        }

        node.isWord = true;
    }

    public boolean search(String word) {
        return dfs(root, word, 0);
    }

    private boolean dfs(TrieNode node, String word, int index) {
        if (index == word.length()) {
            return node.isWord;
        }

        char c = word.charAt(index);

        if (c != '.') {
            TrieNode next = node.children[c - 'a'];

            return next != null && dfs(next, word, index + 1);
        }

        // '.' can match any lowercase letter.
        for (TrieNode next : node.children) {
            if (next != null && dfs(next, word, index + 1)) {
                return true;
            }
        }

        return false;
    }
}