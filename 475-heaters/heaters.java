class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        java.util.Arrays.sort(houses);
        java.util.Arrays.sort(heaters);

        long radius = 0;

        for (int house : houses) {
            int pos = java.util.Arrays.binarySearch(heaters, house);

            if (pos >= 0) {
                // House is exactly at a heater
                continue;
            }

            // binarySearch returns -(insertion point) - 1
            int right = -pos - 1;

            long leftDist = Long.MAX_VALUE;
            long rightDist = Long.MAX_VALUE;

            // Nearest heater on the left
            if (right > 0) {
                leftDist = (long) house - heaters[right - 1];
            }

            // Nearest heater on the right
            if (right < heaters.length) {
                rightDist = (long) heaters[right] - house;
            }

            // This house needs at least this much radius
            radius = Math.max(radius, Math.min(leftDist, rightDist));
        }

        return (int) radius;
    }
}