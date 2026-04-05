public class RobotReturnToOrigin {
    class Solution {
    public boolean judgeCircle(String moves) {

        // x tracks horizontal net displacement: R=+1, L=-1
        // y tracks vertical net displacement:   U=+1, D=-1
        int x = 0, y = 0;

        for (char move : moves.toCharArray()) {
            // toCharArray() converts the String into a char[]
            // so we can use the enhanced for-loop (cleaner than index-based)

            if (move == 'R') x++;       // Move right: +1 on x-axis
            else if (move == 'L') x--;  // Move left:  -1 on x-axis (cancels R)
            else if (move == 'U') y++;  // Move up:    +1 on y-axis
            else if (move == 'D') y--;  // Move down:  -1 on y-axis (cancels U)
        }

        // Both net displacements must be zero = back at origin
        return x == 0 && y == 0;
    }
}

/* Cleanest Solution */
/* class Solution {
    public boolean judgeCircle(String moves) {

        // Create 26 boxes, one for each letter A to Z
        // All boxes start at 0 (Java does this automatically)
        // Box 0 = 'A', Box 1 = 'B', ..., Box 3 = 'D', 
        // Box 11 = 'L', Box 17 = 'R', Box 20 = 'U'
        int[] ch = new int[26];

        // moves.toCharArray() converts "UDLR" → ['U','D','L','R']
        // so we can loop over each character one by one
        // 'move' holds the current character each iteration
        for (char move : moves.toCharArray()) {

            // 'move' is secretly a number (ASCII value)
            // 'A' is secretly 65
            // So move - 'A' gives us the box index for this letter
            // Example: 'U' - 'A' = 85 - 65 = 20 → go to box 20
            // Example: 'D' - 'A' = 68 - 65 =  3 → go to box  3
            // Example: 'L' - 'A' = 76 - 65 = 11 → go to box 11
            // Example: 'R' - 'A' = 82 - 65 = 17 → go to box 17
            // ++ means: add 1 to that box (count this character)
            ch[move - 'A']++;

        }
        // After the loop for "UDLR":
        // ch[20] = 1  (saw U once)
        // ch[3]  = 1  (saw D once)
        // ch[11] = 1  (saw L once)
        // ch[17] = 1  (saw R once)

        // For robot to be back at origin:
        // Every U must have a matching D → their counts must be equal
        // Every L must have a matching R → their counts must be equal
        // ch['U'-'A'] = ch[20] = 1  (number of U's)
        // ch['D'-'A'] = ch[3]  = 1  (number of D's)
        // ch['L'-'A'] = ch[11] = 1  (number of L's)
        // ch['R'-'A'] = ch[17] = 1  (number of R's)
        // 1 == 1 && 1 == 1 → true → robot is back at origin
        return ch['U' - 'A'] == ch['D' - 'A'] &&
               ch['L' - 'A'] == ch['R' - 'A'];
    }
}

/*

DRY RUN — moves = "UDLR"
================================

Start: ch = [0, 0, 0, 0, 0, ... all 26 zeros]

--- Iteration 1: move = 'U' ---
'U' - 'A' = 85 - 65 = 20
ch[20]++  →  ch[20] = 1
ch = [..., ch[3]=0, ..., ch[11]=0, ..., ch[17]=0, ..., ch[20]=1, ...]

--- Iteration 2: move = 'D' ---
'D' - 'A' = 68 - 65 = 3
ch[3]++   →  ch[3] = 1
ch = [..., ch[3]=1, ..., ch[11]=0, ..., ch[17]=0, ..., ch[20]=1, ...]

--- Iteration 3: move = 'L' ---
'L' - 'A' = 76 - 65 = 11
ch[11]++  →  ch[11] = 1
ch = [..., ch[3]=1, ..., ch[11]=1, ..., ch[17]=0, ..., ch[20]=1, ...]

--- Iteration 4: move = 'R' ---
'R' - 'A' = 82 - 65 = 17
ch[17]++  →  ch[17] = 1
ch = [..., ch[3]=1, ..., ch[11]=1, ..., ch[17]=1, ..., ch[20]=1, ...]

--- Return ---
ch['U'-'A'] == ch['D'-'A']  →  ch[20] == ch[3]  →  1 == 1  ✅
ch['L'-'A'] == ch['R'-'A']  →  ch[11] == ch[17] →  1 == 1  ✅
true && true = true ✅  Robot is back at origin!


DRY RUN — moves = "LL"
================================

Start: ch = [0, 0, 0, ... all 26 zeros]

--- Iteration 1: move = 'L' ---
'L' - 'A' = 76 - 65 = 11
ch[11]++  →  ch[11] = 1

--- Iteration 2: move = 'L' ---
'L' - 'A' = 11  (same calculation)
ch[11]++  →  ch[11] = 2

--- Return ---
ch['U'-'A'] == ch['D'-'A']  →  ch[20] == ch[3]  →  0 == 0  ✅
ch['L'-'A'] == ch['R'-'A']  →  ch[11] == ch[17] →  2 == 0  ❌
true && false = false ❌  Robot is NOT back at origin!

 */
}
