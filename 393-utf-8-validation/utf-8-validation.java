class Solution {
    public boolean validUtf8(int[] data) {
        int remaining = 0;

        for (int num : data) {
            if (remaining == 0) {
                if ((num & 0x80) == 0) {
                    // 1-byte character: 0xxxxxxx
                    continue;
                } 
                else if ((num & 0xE0) == 0xC0) {
                    // 2-byte character: 110xxxxx
                    remaining = 1;
                } 
                else if ((num & 0xF0) == 0xE0) {
                    // 3-byte character: 1110xxxx
                    remaining = 2;
                } 
                else if ((num & 0xF8) == 0xF0) {
                    // 4-byte character: 11110xxx
                    remaining = 3;
                } 
                else {
                    return false;
                }
            } 
            else {
                // Continuation byte must be 10xxxxxx
                if ((num & 0xC0) != 0x80) {
                    return false;
                }

                remaining--;
            }
        }

        return remaining == 0;
    }
}