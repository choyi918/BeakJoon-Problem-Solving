package BarkingDog.Hexa09;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/5427
 *  다시 풀어봐야하는 문제: 세세한 조건식을 한번에 세우기 힘들었음 - 테스트 케이스 여러번 돌려봐야 가능했음
 */

public class BasicFire {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        int testCaseCount = Integer.parseInt(br.readLine());

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        for (int i = 0; i < testCaseCount; i++) {
            StringTokenizer boardSpec = new StringTokenizer(br.readLine());

            int ySize = Integer.parseInt(boardSpec.nextToken());
            int xSize = Integer.parseInt(boardSpec.nextToken());

            char[][] board = new char[xSize][ySize];

            Queue<Pos> fireQueue = new LinkedList();
            Queue<Pos> sangQueue = new LinkedList();

            int[][] fireDistance = new int[xSize][ySize];
            int[][] sangDistance = new int[xSize][ySize];

            for (int x = 0; x < xSize; x++) {
                String[] boardLine = br.readLine().split("");
                for (int y = 0; y < ySize; y++) {
                    char factor = boardLine[y].charAt(0);

                    fireDistance[x][y] = -1;
                    sangDistance[x][y] = -1;

                    if (factor == '@') {
                        sangDistance[x][y] = 0;
                        sangQueue.add(new Pos(x, y));
                    } else if (factor == '*') {
                        fireDistance[x][y] = 0;
                        fireQueue.add(new Pos(x, y));
                    }

                    board[x][y] = factor;
                }
            }

            while (!fireQueue.isEmpty()) {
                Pos popped = fireQueue.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = popped.x + dx[j];
                    int ny = popped.y + dy[j];

                    if (nx < 0 || nx >= xSize || ny < 0 || ny >= ySize)
                        continue;

                    if (board[nx][ny] == '#' || board[nx][ny] == '*')
                        continue;

                    if (fireDistance[nx][ny] > 0)
                        continue;

                    fireDistance[nx][ny] = fireDistance[popped.x][popped.y] + 1;
                    fireQueue.add(new Pos(nx, ny));

                }
            }


            boolean isPossible = false;
            while (!sangQueue.isEmpty()) {
                Pos popped = sangQueue.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = popped.x + dx[j];
                    int ny = popped.y + dy[j];

                    if (nx < 0 || nx >= xSize || ny < 0 || ny >= ySize) {
                        bw.write(String.valueOf(sangDistance[popped.x][popped.y] + 1) + "\n");
                        isPossible = true;
                        break;
                    }

                    if (board[nx][ny] == '#' || board[nx][ny] == '@')
                        continue;

                    if (sangDistance[nx][ny] > 0)
                        continue;

                    if (fireDistance[nx][ny] > -1 && fireDistance[nx][ny] <= sangDistance[popped.x][popped.y] + 1)
                        continue;

                    sangDistance[nx][ny] = sangDistance[popped.x][popped.y] + 1;
                    sangQueue.add(new Pos(nx, ny));
                }

                if (isPossible)
                    break;
            }

//            System.out.println("fireDistance");
//            for (int x = 0; x < xSize; x++) {
//                for (int y = 0; y < ySize; y++) {
//                    System.out.print(fireDistance[x][y] + " ");
//                }
//                System.out.println();
//            }
//
//            System.out.println();
//
//            System.out.println("sangDistance");
//            for (int x = 0; x < xSize; x++) {
//                for (int y = 0; y < ySize; y++) {
//                    System.out.print(sangDistance[x][y] + " ");
//                }
//                System.out.println();
//            }
//
//            System.out.println("----------");

            if (isPossible == false)
                bw.write("IMPOSSIBLE\n");

//            bw.write("\n");
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
5 5
##*##
**##.
*#@..
##.##
.*##.

ans: 3


5
4 3
####
#*@.
####
7 6
###.###
#*#.#*#
#.....#
#.....#
#..@..#
#######
7 4
###.###
#....*#
#@....#
.######
5 5
.....
.***.
.*@*.
.***.
.....
3 3
###
#@#
###

ans:
2
5
IMPOSSIBLE
IMPOSSIBLE
IMPOSSIBLE

1
5 6
*#.##
#...#
#...#
#...#
#.@.#
#####

ans: 5
 */
