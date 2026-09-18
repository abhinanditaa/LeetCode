class Solution {

    private static final String[] BELOW_20 = {
        "", "One", "Two", "Three", "Four", "Five",
        "Six", "Seven", "Eight", "Nine", "Ten",
        "Eleven", "Twelve", "Thirteen", "Fourteen",
        "Fifteen", "Sixteen", "Seventeen", "Eighteen",
        "Nineteen"
    };

    private static final String[] TENS = {
        "", "", "Twenty", "Thirty", "Forty",
        "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    private static final String[] SCALES = {
        "", "Thousand", "Million", "Billion"
    };

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        StringBuilder result = new StringBuilder();

        int scale = 0;

        while (num > 0) {
            int group = num % 1000;

            if (group != 0) {
                String part = convert(group);

                if (scale > 0) {
                    part += " " + SCALES[scale];
                }

                if (result.length() == 0) {
                    result.insert(0, part);
                } else {
                    result.insert(0, part + " ");
                }
            }

            num /= 1000;
            scale++;
        }

        return result.toString();
    }

    private String convert(int num) {
        StringBuilder sb = new StringBuilder();

        if (num >= 100) {
            sb.append(BELOW_20[num / 100])
              .append(" Hundred");

            num %= 100;

            if (num > 0) {
                sb.append(" ");
            }
        }

        if (num >= 20) {
            sb.append(TENS[num / 10]);

            num %= 10;

            if (num > 0) {
                sb.append(" ")
                  .append(BELOW_20[num]);
            }
        } else if (num > 0) {
            sb.append(BELOW_20[num]);
        }

        return sb.toString();
    }
}