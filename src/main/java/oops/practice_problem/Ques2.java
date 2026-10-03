package oops.practice_problem;

public class Ques2 {

    public static void warehouseSummary(int[][] grid) {

        int total = 0;
        int max = grid[0][0];
        int maxRow = 0;
        int maxCol = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                total += grid[i][j];

                if (grid[i][j] > max) {
                    max = grid[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        System.out.println("Total items: " + total);
        System.out.println("Maximum bin value: " + max);
        System.out.println("Maximum bin coordinate: (" + maxRow + ", " + maxCol + ")");
    }

    public static void main(String[] args) {

        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        warehouseSummary(grid);
    }
}