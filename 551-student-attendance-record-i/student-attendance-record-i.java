class Solution {
    public boolean checkRecord(String s) {
        int absences = 0;
        int consecutiveLate = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == 'A') {
                absences++;
            }

            if (c == 'L') {
                consecutiveLate++;
            } else {
                consecutiveLate = 0;
            }

            if (absences >= 2 || consecutiveLate >= 3) {
                return false;
            }
        }

        return true;
    }
}