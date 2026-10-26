
import java.util.*;

class Solution {
    public int findMinDifference(List<String> timePoints) {
        List<Integer> minutes = new ArrayList<>();

        for (String time : timePoints) {
            int h = Integer.parseInt(time.substring(0, 2));
            int m = Integer.parseInt(time.substring(3, 5));
            minutes.add(h * 60 + m);
        }

        Collections.sort(minutes);

        int minDiff = 1440 - minutes.get(minutes.size() - 1)
                            + minutes.get(0);

        for (int i = 1; i < minutes.size(); i++) {
            minDiff = Math.min(minDiff,
                               minutes.get(i) - minutes.get(i - 1));
        }

        return minDiff;
    }
}
