package BarkingDog.Hexa09;

import java.io.*;
import java.util.*;

public class BasicNightMovement {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        int testCaseCount = Integer.parseInt(br.readLine());

        int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
        int[] dy = {2, 1, -1, -2, -2, -1, 1, 2};

        for (int i = 0; i < testCaseCount; i++) {
            int length = Integer.parseInt(br.readLine());
            StringTokenizer nowPos = new StringTokenizer(br.readLine());
            int nowX = Integer.parseInt(nowPos.nextToken());
            int nowY = Integer.parseInt(nowPos.nextToken());

            StringTokenizer targetPos = new StringTokenizer(br.readLine());
            int targetX = Integer.parseInt(targetPos.nextToken());
            int targetY = Integer.parseInt(targetPos.nextToken());

            if (nowX == targetX && nowY == targetY) {
                bw.write(String.valueOf(0) + "\n");
                continue;
            }

            Queue<Pos> queue = new LinkedList<>();
            int[][] distance = new int[length][length];

            for (int x = 0; x < length; x++) {
                for (int y = 0; y < length; y++) {
                    if (x == nowX && y == nowY) {
                        distance[x][y] = 0;
                        queue.add(new Pos(x, y));
                    }
                    else
                        distance[x][y] = -1;
                }
            }


            boolean bBreak = false;
            while(!queue.isEmpty()) {
                Pos popped = queue.poll();

                for (int j = 0; j < dx.length; j++) {
                    int nx = popped.x + dx[j];
                    int ny = popped.y + dy[j];

                    if (nx < 0 || nx >= length || ny < 0 || ny >= length)
                        continue;

                    if (distance[nx][ny] > -1)
                        continue;

                    if (nx == targetX && ny == targetY) {
                        bw.write(String.valueOf(distance[popped.x][popped.y] + 1) + "\n");
                        bBreak = true;
                    }

                    distance[nx][ny] = distance[popped.x][popped.y] + 1;
                    queue.add(new Pos(nx, ny));
                }

                if (bBreak)
                    break;
            }
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

3
8
0 0
7 0
100
0 0
30 50
10
1 1
1 1

ans:
5
28
0


2
300
0 0
0 299
300
0 0
123 123

ans:
151
82


1
4
0 0
1 2

ans:
1


2
4
0 0
0 0
4
1 1
1 1

ans:
0
0

1
10
5 5
6 6

ans: 2

 */
