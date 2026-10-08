import java.util.*;

class Solution {
    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> 
            Integer.compare(b[1] - b[0], a[1] - a[0])
        );

        int energy = 0;
        int current = 0;

        for (int[] task : tasks) {
            int actual = task[0];
            int minimum = task[1];

            energy = Math.max(energy, current + minimum);
            current += actual;
        }

        return energy;
    }
}