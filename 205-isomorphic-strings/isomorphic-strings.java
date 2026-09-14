class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] mapST = new int[128];
        int[] mapTS = new int[128];

        for (int i = 0; i < s.length(); i++) {
            int a = s.charAt(i);
            int b = t.charAt(i);

            if (mapST[a] != mapTS[b]) {
                return false;
            }

            mapST[a] = i + 1;
            mapTS[b] = i + 1;
        }

        return true;
    }
}