class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        HashSet<String> valid = new HashSet<>();

        for (String gene : bank) {
            valid.add(gene);
        }

        if (!valid.contains(endGene)) {
            return -1;
        }

        Queue<String> queue = new ArrayDeque<>();
        queue.offer(startGene);

        HashSet<String> visited = new HashSet<>();
        visited.add(startGene);

        char[] chars = {'A', 'C', 'G', 'T'};
        int mutations = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                if (current.equals(endGene)) {
                    return mutations;
                }

                char[] gene = current.toCharArray();

                for (int j = 0; j < 8; j++) {
                    char original = gene[j];

                    for (char c : chars) {
                        if (c == original) {
                            continue;
                        }

                        gene[j] = c;
                        String next = new String(gene);

                        if (valid.contains(next) && visited.add(next)) {
                            queue.offer(next);
                        }
                    }

                    gene[j] = original;
                }
            }

            mutations++;
        }

        return -1;
    }
}