class Solution {
    public String reverseWords(String s) {
        
        // Remove leading/trailing spaces
        // and split by one or more spaces
        String[] words = s.trim().split("\\s+");

        StringBuilder result = new StringBuilder();

        // Traverse words from right to left
        for (int i = words.length - 1; i >= 0; i--) {
            
            result.append(words[i]);

            // Add a single space between words
            if (i != 0) {
                result.append(" ");
            }
        }

        return result.toString();
    }
}