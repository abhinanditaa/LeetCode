import java.util.*;

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> events = new ArrayList<>(buildings.length * 2);

        // Start = negative height, End = positive height
        for (int[] b : buildings) {
            events.add(new int[]{b[0], -b[2]});
            events.add(new int[]{b[1], b[2]});
        }

        // Sort by x, then by height.
        // At the same x:
        //   starts (-height) come before ends (+height)
        //   taller starts come before shorter starts
        //   shorter ends come before taller ends
        events.sort((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        List<List<Integer>> result = new ArrayList<>();

        // Max heap of active building heights
        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        maxHeap.offer(0);

        int previousHeight = 0;
        int i = 0;

        while (i < events.size()) {
            int x = events.get(i)[0];

            // Process every event at the same x-coordinate
            while (i < events.size() && events.get(i)[0] == x) {
                int h = events.get(i)[1];

                if (h < 0) {
                    // Building starts
                    maxHeap.offer(-h);
                } else {
                    // Building ends
                    maxHeap.remove(h);
                }

                i++;
            }

            int currentHeight = maxHeap.peek();

            // Skyline height changed
            if (currentHeight != previousHeight) {
                result.add(Arrays.asList(x, currentHeight));
                previousHeight = currentHeight;
            }
        }

        return result;
    }
}