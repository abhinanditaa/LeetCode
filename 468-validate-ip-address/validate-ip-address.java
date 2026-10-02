class Solution {
    public String validIPAddress(String queryIP) {
        if (queryIP.indexOf('.') >= 0) {
            return isIPv4(queryIP) ? "IPv4" : "Neither";
        }

        if (queryIP.indexOf(':') >= 0) {
            return isIPv6(queryIP) ? "IPv6" : "Neither";
        }

        return "Neither";
    }

    private boolean isIPv4(String s) {
        String[] parts = s.split("\\.", -1);

        if (parts.length != 4) {
            return false;
        }

        for (String part : parts) {
            int len = part.length();

            if (len == 0 || len > 3) {
                return false;
            }

            // Leading zero
            if (len > 1 && part.charAt(0) == '0') {
                return false;
            }

            int num = 0;

            for (int i = 0; i < len; i++) {
                char c = part.charAt(i);

                if (c < '0' || c > '9') {
                    return false;
                }

                num = num * 10 + (c - '0');
            }

            if (num > 255) {
                return false;
            }
        }

        return true;
    }

    private boolean isIPv6(String s) {
        String[] parts = s.split(":", -1);

        if (parts.length != 8) {
            return false;
        }

        for (String part : parts) {
            int len = part.length();

            if (len < 1 || len > 4) {
                return false;
            }

            for (int i = 0; i < len; i++) {
                char c = part.charAt(i);

                if (!((c >= '0' && c <= '9') ||
                      (c >= 'a' && c <= 'f') ||
                      (c >= 'A' && c <= 'F'))) {
                    return false;
                }
            }
        }

        return true;
    }
}