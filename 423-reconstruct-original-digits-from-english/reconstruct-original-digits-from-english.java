class Solution {
    private int helper(int[] map, char key, String spell){
        int val = map[key - 'a'];
        for(char c: spell.toCharArray()) map[c - 'a'] -= val;
        return val;
    }
    public String originalDigits(String s) {
        int map[] = new int[26], cnt[] = new int[10];
        for(char c: s.toCharArray()) map[c - 'a']++;
        
        cnt[0] = helper(map, 'z', "zero");
        cnt[2] = helper(map, 'w', "two");
        cnt[4] = helper(map, 'u', "four");
        cnt[6] = helper(map, 'x', "six");
        cnt[8] = helper(map, 'g', "eight");

        cnt[1] = helper(map, 'o', "one");
        cnt[3] = helper(map, 't', "three");
        cnt[5] = helper(map, 'f', "five");
        cnt[7] = helper(map, 's', "seven");

        cnt[9] = helper(map, 'i', "nine");

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<=9; i++)
            sb.append(("" + i).repeat(cnt[i]));
        return sb.toString();
    }
}