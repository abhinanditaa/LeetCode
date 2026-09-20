class Solution {
    public void wiggleSort(int[] nums) {
        int n = nums.length;

        // Find median using QuickSelect
        int median = quickSelect(nums, (n - 1) / 2);

        int left = 0;
        int i = 0;
        int right = n - 1;

        while (i <= right) {
            int mapped = index(i, n);

            if (nums[mapped] > median) {
                swap(nums, index(left++, n), mapped);
                i++;
            } else if (nums[mapped] < median) {
                swap(nums, index(right--, n), mapped);
            } else {
                i++;
            }
        }
    }

    // Virtual index: 0, 2, 4, 6, 1, 3, 5, ...
    private int index(int i, int n) {
        return (1 + 2 * i) % (n | 1);
    }

    // QuickSelect: kth smallest element
    private int quickSelect(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int pivotIndex = left + (right - left) / 2;
            int pivot = nums[pivotIndex];

            int i = left;
            int j = right;

            while (i <= j) {
                while (nums[i] < pivot) i++;
                while (nums[j] > pivot) j--;

                if (i <= j) {
                    swap(nums, i, j);
                    i++;
                    j--;
                }
            }

            if (k <= j) {
                right = j;
            } else if (k >= i) {
                left = i;
            } else {
                return nums[k];
            }
        }

        return nums[k];
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}