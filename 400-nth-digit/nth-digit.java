class Solution {
    public int findNthDigit(int n) {
        long digits = 1;
        long count = 9;
        long start = 1;

        // Find the digit-length group containing n
        while (n > digits * count) {
            n -= digits * count;
            digits++;
            count *= 10;
            start *= 10;
        }

        // Find the actual number containing the nth digit
        long number = start + (n - 1) / digits;

        // Find the required digit inside that number
        int index = (int) ((n - 1) % digits);

        return String.valueOf(number).charAt(index) - '0';
    }
}