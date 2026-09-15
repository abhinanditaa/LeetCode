import java.util.*;

class Solution {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    private final int[][] directions = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();

        // Build Trie
        for (String word : words) {
            TrieNode node = root;

            for (char c : word.toCharArray()) {
                int index = c - 'a';

                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }

                node = node.children[index];
            }

            node.word = word;
        }

        List<String> result = new ArrayList<>();
        int rows = board.length;
        int cols = board[0].length;

        // Start DFS from every cell
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dfs(board, r, c, root, result);
            }
        }

        return result;
    }

    private void dfs(char[][] board, int r, int c,
                     TrieNode node, List<String> result) {

        char ch = board[r][c];

        if (ch == '#') {
            return;
        }

        TrieNode next = node.children[ch - 'a'];

        // Current path is not a prefix of any word
        if (next == null) {
            return;
        }

        // Complete word found
        if (next.word != null) {
            result.add(next.word);

            // Prevent finding the same word again
            next.word = null;
        }

        // Mark current cell as visited
        board[r][c] = '#';

        for (int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr >= 0 && nr < board.length &&
                nc >= 0 && nc < board[0].length &&
                board[nr][nc] != '#') {

                dfs(board, nr, nc, next, result);
            }
        }

        // Restore cell
        board[r][c] = ch;

        /*
         * Optional Trie pruning:
         * If this node has no remaining children and no word,
         * it can be disconnected from its parent.
         */
        if (next.word == null && isEmpty(next)) {
            node.children[ch - 'a'] = null;
        }
    }

    private boolean isEmpty(TrieNode node) {
        for (TrieNode child : node.children) {
            if (child != null) {
                return false;
            }
        }

        return true;
    }
}