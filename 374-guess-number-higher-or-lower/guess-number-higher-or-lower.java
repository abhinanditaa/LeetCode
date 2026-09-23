public class Solution extends GuessGame {
    public int guessNumber(int n) {
        long left = 1;
        long right = n;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            int result = guess((int) mid);

            if (result == 0) {
                return (int) mid;
            } else if (result < 0) {
                // Our guess is higher than pick
                right = mid - 1;
            } else {
                // Our guess is lower than pick
                left = mid + 1;
            }
        }

        return -1;
    }
}