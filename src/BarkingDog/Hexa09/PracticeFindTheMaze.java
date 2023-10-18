package BarkingDog.Hexa09;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class PracticeFindTheMaze {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        {
            int[] dx = {-1, 0, 1, 0};
            int[] dy = {0, -1, 0, 1};

            String[] boardSpec = br.readLine().split(" ");

            int n = Integer.parseInt(boardSpec[0]);
            int m = Integer.parseInt(boardSpec[1]);

            int[][] board = new int[n][m];
            int[][] distance = new int[n][m];

            for (int x = 0; x < n; x++) {
                String[] split = br.readLine().split("");

                for (int y = 0; y < m; y++)
                    board[x][y] = Integer.parseInt(split[y]);
            }

            Queue<Pos> queue = new LinkedList<>();
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {
                    queue.add(new Pos(x, y));

                    while (queue.isEmpty() == false) {
                        Pos popped = queue.poll();

                        for (int i = 0; i < 4; i++) {
                            int nx = popped.x + dx[i];
                            int ny = popped.y + dy[i];

                            if (nx < 0 || nx >= n)
                                continue;

                            if (ny < 0 || ny >= m)
                                continue;

                            if (board[nx][ny] == 0)
                                continue;

                            if (distance[nx][ny] > 0)
                                continue;

                            distance[nx][ny] = distance[popped.x][popped.y] + 1;
                            queue.add(new Pos(nx, ny));
                        }
                    }
                }
            }

            bw.write(String.valueOf(distance[n - 1][m - 1] + 1));
            bw.flush();
        }

        isr.close();
        osw.close();
        br.close();
        bw.close();
    }

    static class Pos {
        int x;
        int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
