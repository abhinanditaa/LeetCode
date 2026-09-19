class NumArray {
    private final int[] tree;
    private final int[] nums;

    public NumArray(int[] nums) {
        this.nums = nums.clone();
        this.tree = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            add(i + 1, nums[i]);
        }
    }

    private void add(int index, int value) {
        while (index < tree.length) {
            tree[index] += value;
            index += index & -index;
        }
    }

    public void update(int index, int val) {
        int difference = val - nums[index];
        nums[index] = val;

        add(index + 1, difference);
    }

    private int prefixSum(int index) {
        int sum = 0;

        while (index > 0) {
            sum += tree[index];
            index -= index & -index;
        }

        return sum;
    }

    public int sumRange(int left, int right) {
        return prefixSum(right + 1) - prefixSum(left);
    }
}