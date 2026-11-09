
class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Set<String> roots = new HashSet<>(dictionary);
        StringBuilder result = new StringBuilder();

        String[] words = sentence.split(" ");

        for (String word : words) {
            if (result.length() > 0) {
                result.append(" ");
            }

            String root = word;

            for (int i = 1; i <= word.length(); i++) {
                String prefix = word.substring(0, i);

                if (roots.contains(prefix)) {
                    root = prefix;
                    break;
                }
            }

            result.append(root);
        }

        return result.toString();
    }
}
