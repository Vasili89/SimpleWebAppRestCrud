public class Rover {

    public static void calculateRoverPath(int[][] map) {
        int m = map.length;
        int n = map[0].length;
        int[][] newMap = new int[m][n];
        newMap[0][0] = 0;
        int WEST, EAST, NORTH, SOUTH;
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    NORTH = 1 + newMap[i][j] + step(map[i][j] - map[i - 1][j]);
                    if (newMap[i - 1][j] == 0) newMap[i - 1][j] = NORTH;
                    else if (newMap[i - 1][j] > NORTH) {
                        newMap[i - 1][j] = NORTH;
                        i = 0;
                        j = 0;
                    }
                }
                if (j > 0) {
                    WEST = 1 + newMap[i][j] + step(map[i][j] - map[i][j - 1]);
                    if (newMap[i][j - 1] == 0) newMap[i][j - 1] = WEST;
                    else if (newMap[i][j - 1] > WEST) {
                        newMap[i][j - 1] = WEST;
                        i = 0;
                        j = 0;
                    }
                }
                if (i < (m-1)) {
                    SOUTH = 1 + newMap[i][j] + step(map[i][j] - map[i + 1][j]);
                    if (newMap[i + 1][j] == 0) newMap[i + 1][j] = SOUTH;
                    else if (newMap[i + 1][j] > SOUTH) {
                        newMap[i + 1][j] = SOUTH;
                        i = 0;
                        j = 0;
                    }
                }
                if (j < (n-1)) {
                    EAST = 1 + newMap[i][j] + step(map[i][j] - map[i][j + 1]);
                    if (newMap[i][j + 1] == 0) newMap[i][j + 1] = EAST;
                    else if (newMap[i][j + 1] > EAST) {
                        newMap[i][j + 1] = EAST;
                        i = 0;
                        j = 0;
                    }
                }
            }

        }
        newMap[0][0] = 0;
    }

    public static int step(int value) {
        if (value < 0) value = value * (-1);
        return value;
    }
}
