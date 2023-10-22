package BarkingDog.Hexa09;

import java.io.*;
import java.util.*;

public class AppliedWallBreakingAndMovement {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        StringTokenizer boardSpec = new StringTokenizer(br.readLine());

        int xSize = Integer.parseInt(boardSpec.nextToken());
        int ySize = Integer.parseInt(boardSpec.nextToken());

        int[][] board = new int[xSize][ySize];
        int[][][] distance = new int[xSize][ySize][2];

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        for (int x = 0; x < xSize; x++) {
            String[] boardLine = br.readLine().split("");
            for (int y = 0; y < ySize; y++) {
                board[x][y] = Integer.parseInt(boardLine[y]);

                if (!(x == 0 && y == 0))
                    distance[x][y][0] = distance[x][y][1] = -1;
            }
        }

        Queue<Pos2> queue = new LinkedList<>();
        queue.add(new Pos2(0, 0, false));

        while (!queue.isEmpty()) {
            Pos2 popped = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = popped.x + dx[i];
                int ny = popped.y + dy[i];
                boolean broken = popped.broken;

                if (nx < 0 || nx >= xSize || ny < 0 || ny >= ySize)
                    continue;

                int nextDist = distance[popped.x][popped.y][broken ? 1 : 0] + 1;

                if (board[nx][ny] == 0 && distance[nx][ny][broken ? 1 : 0] == -1)  {
                    distance[nx][ny][broken ? 1 : 0] = nextDist;
                    queue.add(new Pos2(nx, ny, broken));
                }

                if (broken == false && board[nx][ny] == 1 && distance[nx][ny][1] == -1) {
                    distance[nx][ny][1] = nextDist;
                    queue.add(new Pos2(nx, ny, true));
                }
            }
        }

        //for print
        System.out.println("------");
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++)
                System.out.print(distance[x][y][0] + " ");
            System.out.println();
        }

        System.out.println();

        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++)
                System.out.print(distance[x][y][1] + " ");
            System.out.println();
        }
        System.out.println("------");


        int dist1 = distance[xSize - 1][ySize - 1][0];
        System.out.println("dist1 = " + dist1);
        int dist2 = distance[xSize - 1][ySize - 1][1];
        System.out.println("dist2 = " + dist2);

        if (dist1 == -1 && dist2 == -1)
            bw.write(String.valueOf(-1));
        else if (dist1 == -1 && dist2 != -1)
            bw.write(String.valueOf(dist2 + 1));
        else if (dist1 != -1 && dist2 == -1)
            bw.write(String.valueOf(dist1 + 1));
        else
            bw.write(String.valueOf(Math.min(dist1, dist2) + 1));

        bw.flush();
    }

    static class Pos2 {
        int x;
        int y;
        boolean broken;

        public Pos2(int x, int y, boolean broken) {
            this.x = x;
            this.y = y;
            this.broken = broken;
        }
    }


    private void failCode() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        StringTokenizer boardSpec = new StringTokenizer(br.readLine());

        int xSize = Integer.parseInt(boardSpec.nextToken());
        int ySize = Integer.parseInt(boardSpec.nextToken());

        int[][] board = new int[xSize][ySize];
        int[][] distance = new int[xSize][ySize];

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};


        List<Pos> walls = new ArrayList<>();

        // O(NM)
        for (int x = 0; x < xSize; x++) {
            String[] boardLine = br.readLine().split("");

            for (int y = 0; y < ySize; y++) {
                board[x][y] = Integer.parseInt(boardLine[y]);

                if (board[x][y] == 1)
                    walls.add(new Pos(x, y));

                if (!(x == 0 && y == 0))
                    distance[x][y] = -1;
            }
        }

        Queue<Pos> queue = new LinkedList<>();

        queue.add(new Pos(0, 0));

        // O(NM)
        while (!queue.isEmpty()) {
            Pos popped = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = popped.x + dx[i];
                int ny = popped.y + dy[i];

                if (nx < 0 || nx >= xSize || ny < 0 || ny >= ySize)
                    continue;

                if (distance[nx][ny] > -1)
                    continue;

                if (board[nx][ny] == 1)
                    continue;

                distance[nx][ny] = distance[popped.x][popped.y] + 1;
                queue.add(new Pos(nx, ny));
            }
        }

        int shortest = distance[xSize - 1][ySize - 1] + 1;

//        System.out.println("shortest = " + shortest);
        if (shortest == 0)
            shortest = xSize * ySize + 1;

        // O(NM * walls.size) -> (NM + NM) * walls.size
        for (int i = 0; i < walls.size(); i++) {
            Pos wall = walls.get(i);

            board[wall.x][wall.y] = 0;

            int[][] distance1 = new int[xSize][ySize];

            for (int x = 0; x < xSize; x++) {
                for (int y = 0; y < ySize; y++) {
                    if (!(x == 0 && y == 0))
                        distance1[x][y] = -1;
                }
            }

            queue.clear();
            queue.add(new Pos(0, 0));

            while (!queue.isEmpty()) {
                Pos popped = queue.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = popped.x + dx[j];
                    int ny = popped.y + dy[j];

                    if (nx < 0 || nx >= xSize || ny < 0 || ny >= ySize)
                        continue;

                    if (distance1[nx][ny] > -1)
                        continue;

                    if (board[nx][ny] == 1)
                        continue;

                    distance1[nx][ny] = distance1[popped.x][popped.y] + 1;
                    queue.add(new Pos(nx, ny));
                }
            }

            board[wall.x][wall.y] = 1;

            int innerShortest = distance1[xSize - 1][ySize - 1] + 1;

//            System.out.println("innerShortest = " + innerShortest + ", Wall(x, y) = " + "(" + wall.x + ", " + wall.y + ")");

            if (innerShortest != 0 && innerShortest < shortest)
                shortest = innerShortest;
        }

        if (shortest == xSize * ySize + 1)
            bw.write(String.valueOf(-1));
        else
            bw.write(String.valueOf(shortest));

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

//                if ((x != 0) && (y != 0)) // -> 연산자 우선순위 문제인가?
//                if (!(x == 0 && y == 0))
//                    distance[x][y] = -1;

/*
    test case

2 2
01
10

ans: 3

4 4
0101
0101
0001
1110

ans: 7

4 4
0011
0011
1100
1100

ans: 7


9 9
010001000
010101010
010101010
010101010
010101010
010101010
010101010
010101011
000100010

ans: 33

1 1
0

ans: 1

2 4
0111
0010

ans: 5

---------

2 4
0111
0110

ans: -1

----------
x

1 1
0

ans: 1

----------

5 8
01000000
01010000
01010000
01010011
00010010

ans: 20

----------

6 7
0000000
0111111
0100010
0101010
0101010
0001010

ans: 12

---------

8 8
01000100
01010100
01010100
01010100
01010100
01010100
01010100
00010100

ans: 29

----------

3 3
011
111
110

ans: -1

-----------
x

3 6
010000
010111
000110

output: -1
ans: 12

-----------
x

3 3
000
000
000

ans: 5

----------

4 4
0101
0101
0001
1110

ans: 7

5 5
01000
01010
01010
01011
00010

ans: 9

--------

5 5
00000
11101
00001
01111
00010

ans: 15

-------
5 5
01000
01010
01010
01011
00010

ans: 9

------
10 2
01
00
10
00
01
00
10
00
01
00

ans:13

------
5 5
00100
00000
10010
00101
00010

ans:9

------
9 9
010001000
010101010
010101010
010101010
010101010
010101010
010101010
010101011
000100010

ans:33

--------
x

6 6
010001
010101
010101
010101
010110
000110

output: -1
ans:21

---------
5 5
00100
11000
00110
01011
00000

ans:9

------------
8 8
01000100
01010100
01010100
01010100
01010100
01010100
01010100
00010100

답 29


5 10
0000011000
1101011010
0000000010
1111111110
1111000000

답 14


5 5
01100
01000
01110
01000
00010

답 9


8 4
0000
0110
1110
0000
0111
0000
1110
0000

답 11


6 4
0000
1110
0110
0000
0111
0000

답 9


8 8
01000100
01010100
01010100
01010100
01010100
01010100
01010100
00010100

답 29


 */
