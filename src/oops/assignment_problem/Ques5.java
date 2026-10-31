
package oops.assignment_problem.session_11;

import java.util.Queue;
import java.util.LinkedList;

public class Ques5 {

    public static int[][] exitDistance(char[][] plan) {

        int m = plan.length;
        int n = plan[0].length;

        int[][] dist = new int[m][n];

        Queue<int[]> q = new LinkedList<>();

        // Initialize all cells with -1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                dist[i][j] = -1;

                // Add all exits to the queue
                if (plan[i][j] == 'E') {
                    dist[i][j] = 0;
                    q.add(new int[]{i, j});
                }
            }
        }

        // Directions: up, down, left, right
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // Multi-source BFS
        while (!q.isEmpty()) {

            int[] current = q.poll();

            int r = current[0];
            int c = current[1];

            for (int k = 0; k < 4; k++) {

                int nr = r + dr[k];
                int nc = c + dc[k];

                // Check boundaries and open rooms
                if (nr >= 0 && nr < m &&
                    nc >= 0 && nc < n &&
                    plan[nr][nc] != '#' &&
                    dist[nr][nc] == -1) {

                    dist[nr][nc] = dist[r][c] + 1;

                    q.add(new int[]{nr, nc});
                }
            }
        }

        return dist;
    }

    public static void display(int[][] dist) {

        for (int i = 0; i < dist.length; i++) {

            for (int j = 0; j < dist[i].length; j++) {

                System.out.print(dist[i][j] + " ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        char[][] plan = {
            {'.', '#', '.', 'E'},
            {'.', '.', '.', '#'},
            {'E', '#', '.', '.'}
        };

        System.out.println("Exit Distance:");

        int[][] result = exitDistance(plan);

        display(result);
    }
}
