import java.util.*;

class Solution {
    private Map<List<Integer>, Integer> memo = new HashMap<>();

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return dfs(price, special, needs);
    }

    private int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        if (memo.containsKey(needs)) {
            return memo.get(needs);
        }

        int n = needs.size();
        int cost = 0;

        for (int i = 0; i < n; i++) {
            cost += needs.get(i) * price.get(i);
        }

        for (List<Integer> offer : special) {
            List<Integer> remaining = new ArrayList<>();
            boolean valid = true;

            for (int i = 0; i < n; i++) {
                int left = needs.get(i) - offer.get(i);

                if (left < 0) {
                    valid = false;
                    break;
                }

                remaining.add(left);
            }

            if (valid) {
                cost = Math.min(cost, offer.get(n) + dfs(price, special, remaining));
            }
        }

        memo.put(new ArrayList<>(needs), cost);
        return cost;
    }
}