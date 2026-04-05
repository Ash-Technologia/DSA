public class PalindromeNumber {
    class Solution {
    public boolean isPalindrome(int x) {

        // STEP 1: Negative numbers are never palindromes.
        if (x < 0) return false;

        // STEP 2: Numbers ending in 0 (except 0 itself) are never palindromes.
        // Critical for the half-reversal trick: if x ends in 0, the reversed half
        // would start with 0, meaning it can never equal the first half.
        // e.g., x=10 → we'd try to compare 1 with 01, but 01 as an int is just 1.
        // That would give a false positive. So we explicitly reject these.
        if (x != 0 && x % 10 == 0) return false;

        // STEP 3: 'reversedHalf' will hold the reversed second half of x.
        // We build it digit by digit until it's as large as the remaining x.
        int reversedHalf = 0;

        // STEP 4: Keep pulling digits from the back of x into reversedHalf
        // until reversedHalf >= x.
        // At that point, reversedHalf has consumed exactly half the digits.
        // Why '>='? Because once reversed catches up to or surpasses x,
        // we've processed half (for even-length) or just past half (for odd-length).
        while (x > reversedHalf) {

            // STEP 5: Pull the last digit off x.
            // x % 10 extracts the rightmost digit of whatever remains in x.
            // e.g., x=1221: first pass → 1221%10=1
            int digit = x % 10;

            // STEP 6: Append that digit to reversedHalf.
            // Shift reversedHalf left (×10) and place the new digit at the end.
            reversedHalf = reversedHalf * 10 + digit;

            // STEP 7: Drop the last digit from x.
            // Integer division by 10 removes the rightmost digit.
            // e.g., 1221 → 122 → 12 (we stop here because reversedHalf=12 >= x=12)
            x /= 10;
        }

        // STEP 8: Two cases to handle:
        //
        // EVEN-length palindrome (e.g., 1221):
        //   After the loop: x=12, reversedHalf=12
        //   x == reversedHalf → true ✅
        //
        // ODD-length palindrome (e.g., 12321):
        //   After the loop: x=12, reversedHalf=123
        //   The middle digit (3) ended up in reversedHalf — we discard it with /10.
        //   x == reversedHalf / 10 → 12 == 12 → true ✅
        //
        // Why does odd-length work with /10?
        //   In an odd-length palindrome, the middle digit doesn't need to match
        //   anything — it matches itself. So we strip it off reversedHalf before comparing.
        return x == reversedHalf || x == reversedHalf / 10;
    }
}
}
