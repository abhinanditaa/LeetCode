class SummaryRanges {

    private final java.util.TreeMap<Integer, Integer> map;

    public SummaryRanges() {
        map = new java.util.TreeMap<>();
    }

    public void addNum(int value) {
        // Interval on the left
        java.util.Map.Entry<Integer, Integer> left =
                map.floorEntry(value);

        // Already present inside an interval
        if (left != null && left.getValue() >= value) {
            return;
        }

        // Interval on the right
        java.util.Map.Entry<Integer, Integer> right =
                map.ceilingEntry(value);

        boolean connectLeft =
                left != null && left.getValue() + 1 == value;

        boolean connectRight =
                right != null && right.getKey() - 1 == value;

        if (connectLeft && connectRight) {
            // Merge both intervals
            map.put(left.getKey(), right.getValue());
            map.remove(right.getKey());

        } else if (connectLeft) {
            // Extend left interval
            map.put(left.getKey(), value);

        } else if (connectRight) {
            // Extend right interval to include value
            int end = right.getValue();

            map.remove(right.getKey());
            map.put(value, end);

        } else {
            // Create new interval
            map.put(value, value);
        }
    }

    public int[][] getIntervals() {
        int[][] result = new int[map.size()][2];

        int i = 0;

        for (java.util.Map.Entry<Integer, Integer> entry : map.entrySet()) {
            result[i][0] = entry.getKey();
            result[i][1] = entry.getValue();
            i++;
        }

        return result;
    }
}