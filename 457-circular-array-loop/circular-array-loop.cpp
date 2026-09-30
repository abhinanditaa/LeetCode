class Solution {
public:
    // Computes next index in circular array
    // Handles negative modulo correctly in C++
    int calcNextIdx(vector<int>& nums, int cur) {
        int n = nums.size();
        return ((cur + nums[cur]) % n + n) % n;
    }

    bool circularArrayLoop(vector<int>& nums) {
        int n = nums.size();

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) continue; // already processed

            bool isPos = nums[i] > 0;
            int slow = i, fast = i;

            // Floyd's cycle detection
            do {
                slow = calcNextIdx(nums, slow);

                fast = calcNextIdx(nums, fast);
                if ((nums[fast] > 0) != isPos) break;

                fast = calcNextIdx(nums, fast);
                if ((nums[fast] > 0) != isPos) break;

                if (slow == fast) {
                    // self-loop check (cycle length > 1)
                    if (slow != calcNextIdx(nums, slow))
                        return true;
                    else
                        break;
                }
            } while (slow != fast);

            // Mark the path as invalid (0) to avoid reprocessing
            int cur = i;
            if (isPos) {
                while (nums[cur] > 0) {
                    int next = calcNextIdx(nums, cur);
                    nums[cur] = 0;
                    cur = next;
                }
            } else {
                while (nums[cur] < 0) {
                    int next = calcNextIdx(nums, cur);
                    nums[cur] = 0;
                    cur = next;
                }
            }
        }
        return false;
    }
};