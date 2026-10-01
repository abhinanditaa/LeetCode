class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int len1 = s1.length();
        int len2 = s2.length();

        // If s2 contains a character not present in s1,
        // it can never be formed.
        int[] count = new int[26];

        for (char c : s1.toCharArray()) {
            count[c - 'a']++;
        }

        for (char c : s2.toCharArray()) {
            if (count[c - 'a'] == 0) {
                return 0;
            }
        }

        // seen[index in s2] = {number of s1 blocks, number of s2 repetitions}
        int[] seenBlock = new int[len2];
        int[] seenCount = new int[len2];

        java.util.Arrays.fill(seenBlock, -1);

        int s1Blocks = 0;
        int s2Count = 0;
        int index = 0;

        while (s1Blocks < n1) {
            s1Blocks++;

            for (int i = 0; i < len1; i++) {
                if (s1.charAt(i) == s2.charAt(index)) {
                    index++;

                    if (index == len2) {
                        index = 0;
                        s2Count++;
                    }
                }
            }

            // We have seen this state before -> cycle found
            if (seenBlock[index] != -1) {
                int cycleBlocks = s1Blocks - seenBlock[index];
                int cycleCount = s2Count - seenCount[index];

                int remaining = n1 - s1Blocks;
                int cycles = remaining / cycleBlocks;

                s1Blocks += cycles * cycleBlocks;
                s2Count += cycles * cycleCount;
            } else {
                seenBlock[index] = s1Blocks;
                seenCount[index] = s2Count;
            }
        }

        return s2Count / n2;
    }
}