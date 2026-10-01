import java.util.*;

class LFUCache {

    class Node {
        int key, value, freq;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    class DLL {
        Node head, tail;

        DLL() {
            head = new Node(0, 0);
            tail = new Node(0, 0);

            head.next = tail;
            tail.prev = head;
        }

        void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        Node removeLast() {
            if (head.next == tail) {
                return null;
            }

            Node node = tail.prev;
            remove(node);
            return node;
        }

        boolean isEmpty() {
            return head.next == tail;
        }
    }

    private final int capacity;
    private int size;
    private int minFreq;

    private final HashMap<Integer, Node> keyMap;
    private final HashMap<Integer, DLL> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.minFreq = 0;

        keyMap = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = keyMap.get(key);

        if (node == null) {
            return -1;
        }

        increaseFrequency(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        // Key already exists
        if (keyMap.containsKey(key)) {
            Node node = keyMap.get(key);
            node.value = value;

            increaseFrequency(node);
            return;
        }

        // Cache is full
        if (size == capacity) {
            DLL list = freqMap.get(minFreq);
            Node lru = list.removeLast();

            keyMap.remove(lru.key);
            size--;
        }

        // Insert new node
        Node node = new Node(key, value);

        keyMap.put(key, node);

        freqMap.computeIfAbsent(1, k -> new DLL()).addFirst(node);

        minFreq = 1;
        size++;
    }

    private void increaseFrequency(Node node) {
        int oldFreq = node.freq;
        DLL oldList = freqMap.get(oldFreq);

        oldList.remove(node);

        if (oldFreq == minFreq && oldList.isEmpty()) {
            minFreq++;
        }

        node.freq++;

        freqMap.computeIfAbsent(node.freq, k -> new DLL()).addFirst(node);
    }
}