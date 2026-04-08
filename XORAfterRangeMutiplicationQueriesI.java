public class XORAfterRangeMutiplicationQueriesI {
    class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int MOD = 1000000007;

        for (int[] q : queries) {
            int li = q[0];
            int ri = q[1];
            int ki = q[2];
            int vi = q[3];

            int idx = li;

            while (idx <= ri) {
                long updated = ((long) nums[idx] * vi) % MOD;
                nums[idx] = (int) updated;
                idx += ki;
            }
        }

        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        return xor;
    }
}
}