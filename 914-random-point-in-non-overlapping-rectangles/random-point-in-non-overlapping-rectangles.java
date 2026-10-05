class Solution {
    private int[][] rects;
    private long[] prefix;
    private long total;

    public Solution(int[][] rects) {
        this.rects = rects;
        this.prefix = new long[rects.length];

        for (int i = 0; i < rects.length; i++) {
            long width = rects[i][2] - rects[i][0] + 1L;
            long height = rects[i][3] - rects[i][1] + 1L;

            total += width * height;
            prefix[i] = total;
        }
    }

    public int[] pick() {
        long random = (long) (Math.random() * total) + 1;

        int left = 0;
        int right = prefix.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (prefix[mid] >= random) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        int[] r = rects[left];

        int x = r[0] + (int) (Math.random() * (r[2] - r[0] + 1L));
        int y = r[1] + (int) (Math.random() * (r[3] - r[1] + 1L));

        return new int[]{x, y};
    }
}