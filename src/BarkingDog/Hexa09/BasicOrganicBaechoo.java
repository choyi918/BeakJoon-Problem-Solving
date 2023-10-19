package BarkingDog.Hexa09;

import java.io.*;
import java.util.*;

public class BasicOrganicBaechoo {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        int tcCount = Integer.parseInt(br.readLine());
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        for (int i = 0; i < tcCount; i++) {

            StringTokenizer tcSpec = new StringTokenizer(br.readLine());

            int ySize = Integer.parseInt(tcSpec.nextToken());
            int xSize = Integer.parseInt(tcSpec.nextToken());
            int baeChooCount = Integer.parseInt(tcSpec.nextToken());

            int[][] board = new int[xSize][ySize];

            for (int j = 0; j < baeChooCount; j++) {
                StringTokenizer pos = new StringTokenizer(br.readLine());

                int y = Integer.parseInt(pos.nextToken());
                int x = Integer.parseInt(pos.nextToken());

                board[x][y] = 1;
            }

            Queue<Pos> queue = new LinkedList<>();
            int[][] visited = new int[xSize][ySize];

            int result = 0;
            for (int x = 0; x < xSize; x++) {
                for (int y = 0; y < ySize; y++) {
                    int n = board[x][y];

                    if (n == 0)
                        continue;

                    if (visited[x][y] == 1)
                        continue;

                    queue.add(new Pos(x, y));
                    visited[x][y] = 1;
                    result++;

                    while (!queue.isEmpty()) {
                        Pos popped = queue.poll();

                        for (int k = 0; k < 4; k++) {
                            int nx = popped.x + dx[k];
                            int ny = popped.y + dy[k];

                            if (nx < 0 || nx >= xSize || ny < 0 || ny >= ySize)
                                continue;

                            if (board[nx][ny] == 0)
                                continue;

                            if (visited[nx][ny] == 1)
                                continue;

                            queue.add(new Pos(nx, ny));
                            visited[nx][ny] = 1;
                        }
                    }
                }
            }

            bw.write(String.valueOf(result));
            bw.write(System.lineSeparator());
        }

        bw.flush();
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

/*
    test case

1
2 3 5
0 0
1 0
1 1
1 2
0 2

ans: 1

1
3 4 4
0 3
1 0
1 2
1 3

ans: 2

 */
