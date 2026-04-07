    class Robot {

    int width, height;
    int x = 0, y = 0;
    int dir = 0; // 0=East, 1=North, 2=West, 3=South

    String[] dirs = {"East", "North", "West", "South"};
    int[][] moves = {
        {1, 0},   // East
        {0, 1},   // North
        {-1, 0},  // West
        {0, -1}   // South
    };

    int cycle;

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        cycle = 2 * (width + height - 2);
    }

    public void step(int num) {

        num %= cycle;

        // Special edge case when robot returns to (0,0)
        if (num == 0) {
            if (x == 0 && y == 0)
                dir = 3; // South
            return;
        }

        while (num > 0) {

            int nx = x + moves[dir][0];
            int ny = y + moves[dir][1];

            if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                x = nx;
                y = ny;
                num--;
            } else {
                dir = (dir + 1) % 4; // turn counterclockwise
            }
        }
    }

    public int[] getPos() {
        return new int[]{x, y};
    }

    public String getDir() {
        return dirs[dir];
    }
}
