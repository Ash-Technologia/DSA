import java.util.HashMap;

public class TwoSum {
    class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Map to store: number → index
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];  // What number do I NEED?

            // Has the complement been seen before?
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};  // complement's index, current index
            }

            // If not, store current number for future lookups
            map.put(nums[i], i);
        }

        return new int[]{};
    }
}

/*

nums = [2, 7, 11, 15], target = 9
map = {}

i=0, nums[i]=2
  complement = 9 - 2 = 7
  map has 7? ❌ No
  map = {2→0}

i=1, nums[i]=7
  complement = 9 - 7 = 2
  map has 2? ✅ Yes! (at index 0)
  return [0, 1]   ← index of 2, index of 7
  
  */ 
}
