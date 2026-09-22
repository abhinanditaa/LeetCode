class Solution {
    public boolean canMeasureWater(int x, int y, int target) {
        // Total capacity is not enough
        if (target > x + y) {
            return false;
        }

        // Both jugs can be empty, so target 0 is always possible
        if (target == 0) {
            return true;
        }

        // Target must be a multiple of gcd(x, y)
        return target % gcd(x, y) == 0;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}