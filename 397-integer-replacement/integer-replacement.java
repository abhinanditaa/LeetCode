
class Solution {
    public int integerReplacement(int n) {
        long x = n;
        int steps = 0;

        while (x > 1) {
            if ((x & 1) == 0) {
                // Even
                x >>= 1;
            } else if (x == 3 || (x & 2) == 0) {
                // Odd: subtract 1
                x--;
            } else {
                // Odd: add 1
                x++;
            }

            steps++;
        }

        return steps;
    }
}

