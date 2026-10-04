class Solution {
    public boolean checkValidString(String s) {
        int low = 0;   // Minimum possible unmatched '('
        int high = 0;  // Maximum possible unmatched '('

        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } 
            else if (c == ')') {
                low--;
                high--;
            } 
            else { // '*'
                low--;   // Treat '*' as ')'
                high++;  // Treat '*' as '('
            }

            // Even the maximum possibility has too many ')'
            if (high < 0) {
                return false;
            }

            // Minimum cannot be negative
            low = Math.max(low, 0);
        }

        // If 0 is a possible number of unmatched '('
        return low == 0;
    }
}