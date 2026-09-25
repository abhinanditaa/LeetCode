class Solution {
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }

        char[] hex = "0123456789abcdef".toCharArray();
        StringBuilder result = new StringBuilder();

        // Process 8 hexadecimal digits (32 bits)
        for (int i = 0; i < 8; i++) {
            int digit = num & 0xF;
            result.append(hex[digit]);
            num >>>= 4;

            if (num == 0) {
                break;
            }
        }

        return result.reverse().toString();
    }
}