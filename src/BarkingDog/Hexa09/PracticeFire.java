package BarkingDog.Hexa09;

/*
 * https://www.acmicpc.net/problem/4179
 */

import java.io.*;
import java.util.*;

public class PracticeFire {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        {
            StringTokenizer boardSpec = new StringTokenizer(br.readLine());

            int[] dx = {-1, 0, 1, 0};
            int[] dy = {0, -1, 0, 1};

            int xSize = Integer.parseInt(boardSpec.nextToken());
            int ySize = Integer.parseInt(boardSpec.nextToken());

            int[][] fireDistance = new int[xSize][ySize];
            int[][] jihoonDistance = new int[xSize][ySize];

            char[][] board = new char[xSize][ySize];

            Queue<Pos> fireQueue = new LinkedList();
            Queue<Pos> jihoonQueue = new LinkedList();

            for (int x = 0; x < xSize; x++) {
//                StringTokenizer boardLine = new StringTokenizer(br.readLine(), "");
                String[] boardLine = br.readLine().split("");
                for (int y = 0; y < ySize; y++) {
//                    char factor = boardLine.nextToken().charAt(0);
                    char factor = boardLine[y].charAt(0);

                    fireDistance[x][y] = -1;
                    jihoonDistance[x][y] = -1;

                    if (factor == 'J') {
                        jihoonQueue.add(new Pos(x, y));
                        jihoonDistance[x][y] = 0;

                        if (x == 0 || x == xSize - 1 || y == 0 || y == ySize - 1) {
                            bw.write("1");
                            bw.flush();
                            return;
                        }
                    }

                    if (factor == 'F') {
                        fireQueue.add(new Pos(x, y));
                        fireDistance[x][y] = 0;
                    }

                    board[x][y] = factor;
                }
            }

            while (fireQueue.isEmpty() == false) {
                Pos popped = fireQueue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = popped.x + dx[i];
                    int ny = popped.y + dy[i];

                    if (nx < 0 || nx >= xSize)
                        continue;

                    if (ny < 0 || ny >= ySize)
                        continue;

                    if (board[nx][ny] == '#' || fireDistance[nx][ny] > -1)
                        continue;

                    fireDistance[nx][ny] = fireDistance[popped.x][popped.y] + 1;
                    fireQueue.add(new Pos(nx, ny));
                }
            }

            while (jihoonQueue.isEmpty() == false) {
                Pos popped = jihoonQueue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = popped.x + dx[i];
                    int ny = popped.y + dy[i];

//                    if (nx < 0 || nx >= xSize)
//                        continue;

//                    if (ny < 0 || ny >= ySize)
//                        continue;

                    // 위 두 if 문에서 조건에 맞지 않으면 다음 루프를 도는게 아니라, 이미 지훈이 경계선에 도착했다는 뜻으로 보면되므로 여기서 답을 도출하면 됨
                    if (nx < 0 || nx >= xSize || ny < 0 || ny >= ySize) {
                        bw.write(String.valueOf(jihoonDistance[popped.x][popped.y] + 1));
                        bw.flush();
                        return;
                    }


                    if (board[nx][ny] == '#' || jihoonDistance[nx][ny] > -1)
                        continue;

                    jihoonDistance[nx][ny] = jihoonDistance[popped.x][popped.y] + 1;

                    if (fireDistance[nx][ny] > -1
                            && fireDistance[nx][ny] <= jihoonDistance[nx][ny]) {
                        jihoonDistance[nx][ny] = -1;
                        continue;
                    }

                    jihoonQueue.add(new Pos(nx, ny));
                }
            }

            bw.write("IMPOSSIBLE");
            bw.flush();

            // print fire distance
//            for (int x = 0; x < xSize; x++) {
//                for (int y = 0; y < ySize; y++) {
//                    System.out.print(fireDistance[x][y] + " ");
//                }
//                System.out.println();
//            }
//
//            System.out.println();

            // print jihoon distance
//            for (int x = 0; x < xSize; x++) {
//                for (int y = 0; y < ySize; y++) {
//                    System.out.print(jihoonDistance[x][y] + " ");
//                }
//                System.out.println();
//            }

//            int longest = 0;
//            // x = 0
//            for (int y = 0; y < ySize; y++) {
//                if (longest < jihoonDistance[0][y])
//                    longest = jihoonDistance[0][y];
//            }
//
//            // x = xSize - 1
//            for (int y = 0; y < ySize; y++) {
//                if (longest < jihoonDistance[xSize - 1][y])
//                    longest = jihoonDistance[xSize - 1][y];
//            }
//
//            // y = 0
//            for (int x = 0; x < xSize; x++) {
//                if (longest < jihoonDistance[x][0])
//                    longest = jihoonDistance[x][0];
//            }
//
//            // y = ySize - 1
//            for (int x = 0; x < xSize; x++) {
//                if (longest < jihoonDistance[x][ySize - 1])
//                    longest = jihoonDistance[x][ySize - 1];
//            }
//
//            if (longest == 0)
//                bw.write("IMPOSSIBLE");
//            else
//                bw.write(String.valueOf(longest + 1));
//
//            bw.flush();
        }
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

** TEST CASE **

1000 x 1000 에대 한 TC링크
https://icpc.student.cs.uwaterloo.ca/~acm00/090613/data/B.8.dat
ans: 212

2 2
JF
FF

ans: 1

7 7
#######
#J###F#
#.....#
#.#.#.#
#.#.#.#
F.#.#.#
##F.#.#

ans: IMP

7 7
#######
#J#####
#.....#
#.#.#.#
#.#.#.#
F.#.#.#
##F.#.#

ans: 10

5 4
####
#..#
#.##
#.J#
####

ans: IMP

5 4
####
#...
#.##
#.J#
####

ans: 6

5 5
.....
.FFF.
.FJF.
.FFF.
.....

ans: IMPOSSIBLE

7 7
#######
#J###F#
#.....#
#.#.#.#
#.#.#.#
F.#.#.#
#####.#

ans: IMPOSSIBLE

3 3
.JF
...
...

ans: 1

6 5
##.##
#...#
#...#
#...#
#J..#
#####

ans: 6

3 300
############################################################################################################################################################################################################################################################################################################
..........................................................................................................................................................................................................................................................................................................J#
############################################################################################################################################################################################################################################################################################################

ans: 299

3 4
####
#.J#
####

ans: IMPOSSIBLE

3 3
###
#J#
F.#

ans: IMPOSSIBLE

5 5
FFFFF
..J..
.....
.....
.....

ans: 4

4 4
####
#JF#
#..#
#..F

ans: 3

4 4
####
JF.#
#..#
#..#

ans: 1

ans: IMPOSSIBLE

5 5
#F..#
#.J.#
###.#
###.#
###.#

ans: 5

3 4
##.#
FJ.#
##F#

ans: IMPOSSIBLE

3 4
####
#FJ.
####

ans: 2

6 7
###.###
#F#.#F#
#.....#
#.....#
#..J..#
#######

ans: 5

4 102
######################################################################################################
#J....................................................................................................
#F....................................................................................................
######################################################################################################

ans: 101

3 3
###
#J.
#.F

ans: IMPOSSIBLE

4 6
######
......
#.J###
#F####

ans: 5

5 4
####
#...
#.##
#.J#
####

ans: 6

7 7
#######
#J#####
#.....#
#.#.#.#
#.#.#.#
F.#.#.#
##F.#.#

ans: 10

7 7
#######
#J###F#
#.....#
#.#.#.#
#.#.#.#
F.#.#.#
##F.#.#

ans: IMPOSSIBLE
 */