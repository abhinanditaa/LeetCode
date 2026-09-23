import java.util.*;

class Solution {

    private ListNode head;
    private Random random;

    public Solution(ListNode head) {
        this.head = head;
        this.random = new Random();
    }

    public int getRandom() {
        int result = head.val;
        ListNode current = head.next;

        int count = 2;

        while (current != null) {
            if (random.nextInt(count) == 0) {
                result = current.val;
            }

            current = current.next;
            count++;
        }

        return result;
    }
}