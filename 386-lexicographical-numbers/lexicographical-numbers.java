import java.util.*;

class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>(n);
        int current = 1;

        for (int i = 0; i < n; i++) {
            result.add(current);

            // Go deeper: 1 -> 10 -> 100 -> ...
            if (current <= n / 10) {
                current *= 10;
            } 
            else {
                // Move to the next sibling
                while (current % 10 == 9 || current + 1 > n) {
                    current /= 10;
                }
                current++;
            }
        }

        return result;
    }
}