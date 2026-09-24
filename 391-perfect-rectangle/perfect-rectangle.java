import java.util.*;

class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        long area = 0;

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        Set<String> corners = new HashSet<>();

        for (int[] r : rectangles) {
            int x1 = r[0], y1 = r[1];
            int x2 = r[2], y2 = r[3];

            // Calculate total area
            area += (long) (x2 - x1) * (y2 - y1);

            // Find bounding rectangle
            minX = Math.min(minX, x1);
            minY = Math.min(minY, y1);
            maxX = Math.max(maxX, x2);
            maxY = Math.max(maxY, y2);

            // Toggle the four corners
            toggle(corners, x1, y1);
            toggle(corners, x1, y2);
            toggle(corners, x2, y1);
            toggle(corners, x2, y2);
        }

        // Area must exactly match the bounding rectangle
        long boundingArea = (long) (maxX - minX) * (maxY - minY);

        if (area != boundingArea) {
            return false;
        }

        // Exactly four outer corners must remain
        if (corners.size() != 4) {
            return false;
        }

        return corners.contains(minX + "," + minY)
            && corners.contains(minX + "," + maxY)
            && corners.contains(maxX + "," + minY)
            && corners.contains(maxX + "," + maxY);
    }

    private void toggle(Set<String> set, int x, int y) {
        String point = x + "," + y;

        if (!set.add(point)) {
            set.remove(point);
        }
    }
}