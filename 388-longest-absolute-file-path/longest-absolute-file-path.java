class Solution {
    public int lengthLongestPath(String input) {
        String[] lines = input.split("\n");
        int[] pathLength = new int[lines.length + 1];
        int max = 0;

        for (String line : lines) {
            int depth = 0;

            while (depth < line.length() && line.charAt(depth) == '\t') {
                depth++;
            }

            String name = line.substring(depth);

            // Length of current path = parent path + "/" + current name
            pathLength[depth + 1] = pathLength[depth] + name.length() + 1;

            if (name.indexOf('.') != -1) {
                // Remove the extra '/' added after the file name
                max = Math.max(max, pathLength[depth + 1] - 1);
            }
        }

        return max;
    }
}