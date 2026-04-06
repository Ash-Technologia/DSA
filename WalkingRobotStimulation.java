import java.util.HashSet;
import java.util.Set;

public class WalkingRobotStimulation {
    class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {

        // ── Direction tables ──────────────────────────────────────────────
        // Clockwise order: North(0) → East(1) → South(2) → West(3)
        // dx[i] = how x changes when moving in direction i
        // dy[i] = how y changes when moving in direction i
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        // ── Obstacle Set ──────────────────────────────────────────────────
        // Encode (x,y) → single int to avoid string allocation
        // 60001 covers the full y-range [-30000, +30000]
        Set<Integer> obstacleSet = new HashSet<>();
        for (int[] obs : obstacles) {
            obstacleSet.add(obs[0] * 60001 + obs[1]);
        }

        // ── Robot State ───────────────────────────────────────────────────
        int dir = 0;        // Current direction index (0=North)
        int x = 0, y = 0;  // Current position
        int maxDist = 0;    // Maximum squared distance seen

        // ── Process Each Command ──────────────────────────────────────────
        for (int cmd : commands) {

            if (cmd == -1) {
                // Turn right: clockwise = increment index
                dir = (dir + 1) % 4;

            } else if (cmd == -2) {
                // Turn left: counter-clockwise = decrement index
                // +3 avoids Java's negative modulo behavior
                dir = (dir + 3) % 4;

            } else {
                // Move forward up to 'cmd' steps
                for (int step = 0; step < cmd; step++) {

                    // Compute the NEXT cell (don't move yet — check first)
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    // Is the next cell an obstacle?
                    if (obstacleSet.contains(nx * 60001 + ny)) {
                        // Yes — stop this command, stay at current (x,y)
                        break;
                    }

                    // Safe to move
                    x = nx;
                    y = ny;

                    // Update max distance AFTER moving (track every position)
                    maxDist = Math.max(maxDist, x * x + y * y);
                }
            }
        }

        return maxDist;
    }
}
}