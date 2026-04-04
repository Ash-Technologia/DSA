class Solution {
    public boolean containsDuplicate(int[] nums) {

        // Create a building with numbered rooms (buckets)
        HashSet<Integer> seen = new HashSet<>();

        for (int num : nums) {

            // Compute hash → go directly to that bucket → check if num is there
            if (seen.contains(num)) {
                return true;   // Found in bucket → duplicate!
            }

            // Not found → store num in its bucket for future checks
            seen.add(num);
        }

        return false;   // No bucket ever had a pre-existing match
    }
}