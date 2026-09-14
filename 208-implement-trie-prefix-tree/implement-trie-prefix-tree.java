class Trie {

    private static class Node {
        Node[] children = new Node[26];
        boolean isEnd;
    }

    private final Node root = new Node();

    public Trie() {
    }

    public void insert(String word) {
        Node curr = root;

        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (curr.children[index] == null) {
                curr.children[index] = new Node();
            }

            curr = curr.children[index];
        }

        curr.isEnd = true;
    }

    public boolean search(String word) {
        Node node = find(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return find(prefix) != null;
    }

    private Node find(String word) {
        Node curr = root;

        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (curr.children[index] == null) {
                return null;
            }

            curr = curr.children[index];
        }

        return curr;
    }
}