package BarkingDog.Hexa09;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class PracticeTomato {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        {
            int[] dx = {-1, 0, 1, 0};
            int[] dy = {0, -1, 0, 1};

            String[] boardSpec = br.readLine().split(" ");

            int vertical = Integer.parseInt(boardSpec[1]);
            int horizontal = Integer.parseInt(boardSpec[0]);

            int[][] board = new int[vertical][horizontal];
            int[][] distance = new int[vertical][horizontal];
            Queue<Pos> queue = new LinkedList<>();

            for (int x = 0; x < vertical; x++) {
                String[] boardLine = br.readLine().split(" ");

                for (int y = 0; y < horizontal; y++) {
                    int tomato = Integer.parseInt(boardLine[y]);

                    if (tomato == 0)
                        distance[x][y] = -1;
                    else if (tomato == 1)
                        queue.add(new Pos(x, y));

                    board[x][y] = tomato;
                }
            }

            while (queue.isEmpty() == false) {
                Pos popped = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = popped.x + dx[i];
                    int ny = popped.y + dy[i];

                    if (nx < 0 || nx >= vertical)
                        continue;

                    if (ny < 0 || ny >= horizontal)
                        continue;

                    if (board[nx][ny] != 0)
                        continue;

                    if (distance[nx][ny] > -1)
                        continue;

                    distance[nx][ny] = distance[popped.x][popped.y] + 1;
                    queue.add(new Pos(nx, ny));
                }
            }

            boolean isNotPerfect = false;
            int longest = 0;
            for (int x = 0; x < vertical; x++) {
                for (int y = 0; y < horizontal; y++) {
                    if (distance[x][y] == -1) {
                        isNotPerfect = true;
                    }

                    if (longest < distance[x][y])
                        longest = distance[x][y];

                    // System.out.print(distance[x][y] + " ");
                }
                // System.out.println();
            }

            if (isNotPerfect)
                bw.write(String.valueOf(-1));
            else
                bw.write(String.valueOf(longest));

            bw.flush();
        }

        br.close();
        isr.close();
        bw.close();
        osw.close();
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
