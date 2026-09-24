class Solution {
    public int lastRemaining(int n) {
        int head = 1;
        int step = 1;
        boolean left = true;

        while (n > 1) {
            // Head changes when eliminating from the left,
            // or when eliminating from the right with an odd count.
            if (left || (n & 1) == 1) {
                head += step;
            }

            n /= 2;
            step *= 2;
            left = !left;
        }

        return head;
    }
}