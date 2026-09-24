class Solution {
    private final int[] original;
    private final Random random;

    public Solution(int[] nums) {
        original = nums.clone();
        random = new Random();
    }

    public int[] reset() {
        return original.clone();
    }

    public int[] shuffle() {
        int[] arr = original.clone();

        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        return arr;
    }
}