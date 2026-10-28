class Solution {
    public int nextGreaterElement(int n) {
        char[] digits = String.valueOf(n).toCharArray();
        int i = digits.length - 2;

        // Find the first digit smaller than the digit after it
        while (i >= 0 && digits[i] >= digits[i + 1]) {
            i--;
        }

        if (i < 0) {
            return -1;
        }

        // Find the smallest digit greater than digits[i]
        int j = digits.length - 1;
        while (digits[j] <= digits[i]) {
            j--;
        }

        // Swap the two digits
        char temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;

        // Reverse the suffix
        int left = i + 1;
        int right = digits.length - 1;

        while (left < right) {
            temp = digits[left];
            digits[left] = digits[right];
            digits[right] = temp;
            left++;
            right--;
        }

        // Check for 32-bit integer overflow
        long result = Long.parseLong(new String(digits));

        return result > Integer.MAX_VALUE ? -1 : (int) result;
    }
}