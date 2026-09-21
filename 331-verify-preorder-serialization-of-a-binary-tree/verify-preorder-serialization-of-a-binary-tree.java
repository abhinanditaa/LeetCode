class Solution {
    public boolean isValidSerialization(String preorder) {
        int slots = 1;

        for (String node : preorder.split(",")) {
            // Every node consumes one slot
            if (slots == 0) {
                return false;
            }

            if (node.equals("#")) {
                // Null node consumes a slot
                slots--;
            } else {
                // Non-null node consumes one slot
                // and creates two new slots
                slots++;
            }
        }

        return slots == 0;
    }
}