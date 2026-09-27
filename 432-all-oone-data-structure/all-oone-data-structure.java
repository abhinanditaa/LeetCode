class AllOne {

    private static class Node {
        int count;
        HashSet<String> keys;
        Node prev, next;

        Node(int count) {
            this.count = count;
            this.keys = new HashSet<>();
        }
    }

    private final Node head;
    private final Node tail;
    private final HashMap<String, Node> map;

    public AllOne() {
        head = new Node(0);
        tail = new Node(0);

        head.next = tail;
        tail.prev = head;

        map = new HashMap<>();
    }

    public void inc(String key) {
        if (!map.containsKey(key)) {
            Node first = head.next;

            if (first == tail || first.count != 1) {
                Node node = new Node(1);
                insertAfter(head, node);
                first = node;
            }

            first.keys.add(key);
            map.put(key, first);
        } else {
            Node current = map.get(key);
            Node next = current.next;

            if (next == tail || next.count != current.count + 1) {
                Node node = new Node(current.count + 1);
                insertAfter(current, node);
                next = node;
            }

            next.keys.add(key);
            current.keys.remove(key);
            map.put(key, next);

            removeIfEmpty(current);
        }
    }

    public void dec(String key) {
        Node current = map.get(key);

        if (current.count == 1) {
            current.keys.remove(key);
            map.remove(key);
            removeIfEmpty(current);
            return;
        }

        Node prev = current.prev;

        if (prev == head || prev.count != current.count - 1) {
            Node node = new Node(current.count - 1);
            insertAfter(prev, node);
            prev = node;
        }

        prev.keys.add(key);
        current.keys.remove(key);
        map.put(key, prev);

        removeIfEmpty(current);
    }

    public String getMaxKey() {
        if (tail.prev == head) {
            return "";
        }

        return tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        if (head.next == tail) {
            return "";
        }

        return head.next.keys.iterator().next();
    }

    private void insertAfter(Node prev, Node node) {
        node.next = prev.next;
        node.prev = prev;

        prev.next.prev = node;
        prev.next = node;
    }

    private void removeIfEmpty(Node node) {
        if (node.keys.isEmpty()) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }
}