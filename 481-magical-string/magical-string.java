class Solution {
    public int magicalString(int n) {
        if (n <= 0) return 0;
        if (n <= 3) return 1;

        int[] s = new int[n];
        s[0] = 1;
        s[1] = 2;
        s[2] = 2;

        int read = 2;   // Points to the group length
        int write = 3;  // Position where we add new values
        int num = 1;    // Number to append
        int count = 1;  // Number of 1s

        while (write < n) {
            int len = s[read++];

            for (int i = 0; i < len && write < n; i++) {
                s[write++] = num;

                if (num == 1) {
                    count++;
                }
            }

            num = 3 - num; // Toggle 1 <-> 2
        }

        return count;
    }
}