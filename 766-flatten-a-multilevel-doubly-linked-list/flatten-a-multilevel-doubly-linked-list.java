class Solution {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }

        Deque<Node> stack = new ArrayDeque<>();
        stack.push(head);

        Node prev = null;

        while (!stack.isEmpty()) {
            Node curr = stack.pop();

            if (prev != null) {
                prev.next = curr;
                curr.prev = prev;
            }

            // Push next first so child is processed first
            if (curr.next != null) {
                stack.push(curr.next);
            }

            if (curr.child != null) {
                stack.push(curr.child);
                curr.child = null;
            }

            prev = curr;
        }

        return head;
    }
}