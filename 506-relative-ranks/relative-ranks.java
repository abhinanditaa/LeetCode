class Solution {
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;

        Integer[] indices = new Integer[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> Integer.compare(score[b], score[a]));

        String[] answer = new String[n];

        for (int rank = 0; rank < n; rank++) {
            int index = indices[rank];

            if (rank == 0) {
                answer[index] = "Gold Medal";
            } else if (rank == 1) {
                answer[index] = "Silver Medal";
            } else if (rank == 2) {
                answer[index] = "Bronze Medal";
            } else {
                answer[index] = String.valueOf(rank + 1);
            }
        }

        return answer;
    }
}