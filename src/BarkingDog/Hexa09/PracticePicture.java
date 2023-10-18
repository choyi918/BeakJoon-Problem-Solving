package BarkingDog.Hexa09;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/*
 * https://www.acmicpc.net/problem/1926
 */
public class PracticePicture {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        { // solve
            int[] dx = {-1, 0, 1, 0};
            int[] dy = {0, -1, 0, 1};

            int[][] visited = new int[n][m];
            int[][] board = new int[n][m];

            for (int x = 0; x < n; x++) {
                String[] boardLine = br.readLine().split(" ");

                for (int y = 0; y < m; y++)
                    board[x][y] = Integer.parseInt(boardLine[y]);
            }

            Queue<Pos> queue = new LinkedList<>();

            int picCount = 0;
            int bigSize = 0;

            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {

                    if (board[x][y] == 1 && visited[x][y] != 1) {
                        queue.add(new Pos(x, y));
                        visited[x][y] = 1;

                        picCount++;

                        int size = 1;

                        while (queue.isEmpty() == false) {

                            /* for print
                                Iterator<Pos> iterator = queue.iterator();
                                for (int i = 0; i < queue.size(); i++) {
                                    Pos p = iterator.next();
                                    System.out.print(String.format("(%d, %d) ", p.x, p.y));
                                }
                                System.out.println();
                             */

                            Pos popped = queue.poll();

                            if (popped == null) break;

                            for (int p = 0; p < 4; p++) {
                                int nx = popped.x + dx[p];
                                int my = popped.y + dy[p];

                                if (nx < 0 || nx >= n)
                                    continue;

                                if (my < 0 || my >= m)
                                    continue;

                                if (visited[nx][my] == 1)
                                    continue;

                                if (board[nx][my] != 1)
                                    continue;

                                visited[nx][my] = 1;
                                queue.add(new Pos(nx, my));
                                size++;
                            }

                            if (bigSize < size)
                                bigSize = size;
                        }

                        /*  for print
                            System.out.println("bigSize : " + bigSize);
                            System.out.println("picCount : " + picCount);
                            System.out.println("---------");
                         */
                    }
                }
            }

            bw.write(String.valueOf(picCount));
            bw.write(System.lineSeparator());
            bw.write(String.valueOf(bigSize));
            bw.flush();
        }

        isr.close();
        br.close();
        osw.close();
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

    private static void solve(final int n, final int m) {

    }
}
