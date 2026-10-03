class Solution {
    private final double r;
    private final double x;
    private final double y;
    private final java.util.Random random = new java.util.Random();

    public Solution(double radius, double x_center, double y_center) {
        r = radius;
        x = x_center;
        y = y_center;
    }

    public double[] randPoint() {
        while (true) {
            double dx = (random.nextDouble() * 2 - 1) * r;
            double dy = (random.nextDouble() * 2 - 1) * r;

            if (dx * dx + dy * dy <= r * r) {
                return new double[] {x + dx, y + dy};
            }
        }
    }
}