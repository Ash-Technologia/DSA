public class RomanToInteger {
    class Solution {
    private int getValue(char c) {
        // switch on a char is legal in Java — char is treated as its ASCII code
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default:  return 0;
            // default handles anything unexpected — 
            // though the problem guarantees valid input
        }
    }

    public int romanToInt(String s) {
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            int curr = getValue(s.charAt(i));

            // Same peek-ahead logic as before
            if (i + 1 < s.length() && curr < getValue(s.charAt(i + 1))) {
                result -= curr;
            } else {
                result += curr;
            }
        }

        return result;
    }
} 
/* Second solution using HashMap for O(1) lookups without a switch statement 
class Solution {
    public int romanToInt(String s) {

        // HashMap<Character, Integer>:
        //   Key type   → Character (boxed char, needed for generics)
        //   Value type → Integer   (boxed int, needed for generics)
        // We store all 7 symbol→value mappings upfront
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        // map.put(key, value) → hashes 'key', finds bucket, stores pair
        // This runs 7 times regardless of input length → O(1) setup

        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            // map.get(s.charAt(i)):
            //   s.charAt(i)  → char at position i, e.g. 'X'
            //   map.get(...)  → hashes 'X', goes directly to its bucket,
            //                   returns 10
            //   This is O(1) per lookup
            int curr = map.get(s.charAt(i));

            // Same subtractive rule:
            // If there IS a next character (i+1 < s.length())
            // AND curr < next → we're in a subtractive pair (IV, CM, etc.)
            if (i + 1 < s.length() && curr < map.get(s.charAt(i + 1))) {
                result -= curr;
                // e.g. for 'I' in "IV": curr=1, next=5, 1<5 → subtract 1
            } else {
                result += curr;
                // e.g. for 'V' in "IV": curr=5, no next → add 5
                // Net effect of "IV": -1 + 5 = 4 ✓
            }
        }

        return result;
        // Final result accumulates all adds and subtracts in a single pass
    }
}
 */
}
