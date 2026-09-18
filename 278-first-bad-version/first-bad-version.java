public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (isBadVersion(mid)) {
                // mid could be the first bad version
                right = mid;
            } else {
                // mid is definitely not bad
                left = mid + 1;
            }
        }

        return left;
    }
}