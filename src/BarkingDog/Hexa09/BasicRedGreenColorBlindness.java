package BarkingDog.Hexa09;

import java.io.*;
import java.util.*;

public class BasicRedGreenColorBlindness {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        {
            int length = Integer.parseInt(br.readLine());

            char[][] board = new char[length][length];

            for (int x = 0; x < length; x++) {
                String[] boardLine = br.readLine().split("");
                for (int y = 0; y < length; y++) {
                    board[x][y] = boardLine[y].charAt(0);
                }
            }

            int[] dx = {-1, 0, 1, 0};
            int[] dy = {0, -1, 0, 1};

            Queue<Pos> queue = new LinkedList<>();
            char[][] visited = new char[length][length];

            int result1 = 0;
            for (int x = 0; x < length; x++) {
                for (int y = 0; y < length; y++) {
                    if (visited[x][y] == 1)
                        continue;

                    char color = board[x][y];

                    queue.add(new Pos(x, y));
                    visited[x][y] = 1;
                    result1++;

                    while(!queue.isEmpty()) {
                        Pos popped = queue.poll();

                        for (int i = 0; i < 4; i++) {
                            int nx = popped.x + dx[i];
                            int ny = popped.y + dy[i];

                            if (nx < 0 || nx >= length || ny < 0 || ny >= length)
                                continue;

                            if (visited[nx][ny] == 1)
                                continue;

                            if (color != board[nx][ny])
                                continue;

                            queue.add(new Pos(nx, ny));
                            visited[nx][ny] = 1;
                        }
                    }
                }
            }

            queue.clear();
            visited = new char[length][length];

            int result2 = 0;
            for (int x = 0; x < length; x++) {
                for (int y = 0; y < length; y++) {
                    if (visited[x][y] == 1)
                        continue;

                    char mainColor = board[x][y];

                    queue.add(new Pos(x, y));
                    visited[x][y] = 1;
                    result2++;

                    while(!queue.isEmpty()) {
                        Pos popped = queue.poll();

                        for (int i = 0; i < 4; i++) {
                            int nx = popped.x + dx[i];
                            int ny = popped.y + dy[i];

                            if (nx < 0 || nx >= length || ny < 0 || ny >= length)
                                continue;

                            if (visited[nx][ny] == 1)
                                continue;

                            char boardColor = board[nx][ny];

                            if (mainColor == 'B' && mainColor != boardColor)
                                continue;

                            if (mainColor == 'R' && boardColor == 'B')
                                continue;

                            if (mainColor == 'G' && boardColor == 'B')
                                continue;

                            queue.add(new Pos(nx, ny));
                            visited[nx][ny] = 1;
                        }
                    }
                }
            }

            bw.write(String.valueOf(result1));
            bw.write(" ");
            bw.write(String.valueOf(result2));
            bw.flush();
        }
    }

    static class Pos{
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

2
RG
GR

ans: 4 1

2
GR
GR

ans:2 1

3
RRG
GGR
GGR

ans: 4 1

5
RRRRR
RBBBR
RBGBR
RBBBR
RRRRR

ans: 3 3

3
GBG
GBG
RRR

ans: 4 2

3
RGB
RRR
RRR

ans: 3 2

20
BBBBBRRRRRRRRRRRBBBB
BBBBBRRRRRRRRRRRBBBB
RBBBBBRRRRRRRRRRBBBB
RRRBBBBRRRRRRRRRBBBB
RRRBBBBRRRRRRRRRRBRB
GRRBBBBRRRRRRRRRRBRR
GGRRRRBBBRRRRRRRRBBB
GGGRRRBBBRRRRRRRRBBB
RRGGGGBBBRRRRRRRRBBB
BBGGGGBBBBRRRRRRRBBB
BBGGGGGBBBRRRRRRRBBB
GBGGGGGBRRRRRRRRRBBB
GGGGGGGGRRRRRRRRRBBB
GGGGGGGGGRRRRRRRRBBB
GGGGGGGGGGRRRRRRRBBB
RRGGGGGGGGGGRRRRRRBB
RRGGGGGGGGGGGGGGGRBB
RRRGGGGGGGGGGGGGGRBB
GGGGGGGBGGGGGGGGGGBB
RRRRGGGGGGGGGGGGGGGG

ans: 11 6

5
RRRBB
GRBBB
BBBRR
BBRRR
RRRRR

ans: 4 3

75
GGBRRRRBBBRGGGRRRGGGGGRRBBRRRRGBRBBBBGGGBBRGGGGGGGRRRBBRGRRGRRGRRRRGGGBGGGR
GGGGGRRBBBRGGGGRRGGGGGRRRRRRRRRRRBRRRGGGGGRRRRGGGGGRRBBRRRRGRRRRRRRRRGBGGGG
GGGGGRRBBBBBGGGGRRGGGGRRRRRRRRRRRRRRRRRRGGGGGGGGGGGRRBBRRRRGGRRRRRRRRRBBGGG
GGGGGRRRRRBBBBBBBRRRRRRRRRRRRRRRRRRRRRRRRGGGGGGGGGGRRBBBRRRGGRRRRRRRRBBBBBG
BGGGGGRRRRRBBBBBBBBRRRRRRRRRRRRRRRRRRRRRRGGGGGGGGGGGGBBBGGGGGGGRRRRRRRRBBBB
RGGGGGRRRRRRRRBBBBBRRRRRRRRRRRRRRRRRRRRRRGGGGGGGGGGGGBBBBBGGGGGRRRRRRRRBBBB
RRRGGGGRRRRRRRRBBBBBRRRRRRRRRRRRRRRRRRRRRGGGGGGGGGGGGGGBBBGGGGGRRRRRRRRBBBB
RRRGGGGGRRRRRRRBBBBBBRRRRRRRRRRRRRRRRRRRRGGGGGGGGGGGGGGGBBBGGGGGRRRRRRRRRBG
RRRGGGGGGGRRRRRBBBBBBBRRRRRRRRRRRRRRRRRRRGGGGGGGGGGGGGGGBBBGGGGGGGGGRRRRRBG
GRRRRRGGGGRRRRRBBBBBBBBBRRRRRRRRRRRRRRRRRRGGGGGGGGBGGGGGGGBGGGGGGGGGRRRRRBB
RRRRRRGGGGGGRRRRBBBBBBBBBBRRRRRRRRRRRRRRGGGGGGGGGGBGGGGGGGGGGGGGGGGGRRRRRBG
RRRRRRRRGGGGRRRRRBBBBBBGBBBBRRRRRRRRRRRRGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGRRRRR
RRBBBRRRGGGGGRRRRBBBBBBBBBBBRRRRBBBRRRRRRRRGGGGGGGGGGGGGGGGGGGGGGGGGGGRRRRR
RRRBBBRRGGGGGRRRRBGBBBBBBBRRRRRRBBBBRRRRRRRRGGGGGGGGGGGGGGGGGGGGGGGGGGGGRRR
BBRBBBRRGGGGGRRRRRRBBBBBBBBRRRRRBBBBRRRRRRRRRGGGGGGGGGGGGGGGGGGGGGGGGGGGRRR
BBRRBBBBGGGGGRRRRRRBBBBBBBBRRRRRBBBBBRRRRRRRRGGGGGGGGGGGGGGGGGGGGGGGGGGGRRR
GBRGGGGGGGGGGRRRRRRRBBBBBBBGRRRRRBBBBRRRRRRRRRRRRRRRGGGGGGGGGGGGGGGGGGGGGGR
GGGGGGGGGGGGGRRRRRRRRRRRBBBBGGGRRBBBBRRRRRRRRRRRRRRRRGGGGGGGRRRRRGGGGGGGGGR
GGGGGGRRRGGGGGGRRRRRRRRRRRBBGGGRRBBBBBRRRRRRRRRRRRRRRRRRGGGGGRRRRGGGGGGGGGR
GGGGGGGRRGGGGGGGGGRRRRRRRRBBBBGRRRRBBBRRRRRRRRBBRRRRRRRBBGGGGGRRBBGGGGGGGGR
GGGGGGGRRRGGGGBGGGRRRRRRRRBGBBBBBRRRBBRRRRRRRRRRRRRRRRRBBGGGGGRRRBBGGGGGGGR
BGGGGGGGGGGGGGGGGGGGGRRRRRBGGBBBBBBBBBRRRRRRRRRRRRRRRRRRBBGGGGRRRBBGGGGGGGG
GGGGGGGGGGGGGGGGGGGGGGGRRRRGGBBBBBBBBBBRRRRRRRRRRRRRRRRRBBGGGGRRRRRRGGGGGGG
BBGGGGGGGGGGGGGGGGGGGGGGGRRGGGBBBBBBBRBRRRRRRRRRRRRRRRRRBBBGGGGGGGRRRGGGGGG
GBGGGGGGGGGGGGGGGGGGGGGGGRRGGGBBBBBBBRRRRRRRRRRRRRRRRRRRBBBBBBGGGGRRRRRRRGG
RRGGGGGGGGGGGGGGGGGGGGGGGGRRGGRRRRBBBRRRRRRRRRRRRRRRRRRRRRRRBBBBGGGRRRRRRRG
RRRRRRRGGGGGGGGGGGGGGGGGGGGGGGRRRRBBBBBRRRRRRRRRRRRRRRRRRRRRBBBBGGGGGGRRRRR
BRRRRRRRGGGGGGGGGGGGGGGGGGGGGGGRRRBBBBBRRRRRRRRRRRRRRRRRRRRRBBBBGGGGGGRRRRR
BRRRRRRRGGGGGGGGGGGGGGGGGGGGGGGGGRBBBBBRRGBBBBRRRRRRRRRRRRRRBBBBGGGGGGGRRRR
BRRRRRRRRRRGGRGGGGGGGGGGGGGGGGGGGGGBBBBRRGGGBBBRRRRRRRRRRRRRBBBBGGGGGGGRRRR
BBRRRRRRRRRRRRGGGGGGGGGGGGGRGGGGGGGGGBBBBBBGGGGGRRRRRRRRRRRRRBBBBBBBGGGGGRR
BBBBRGRBBRRRRRRBBGGGGGGGGGGRGGGGGGGGGGBBBBBGGGGGRRRRRRRRRRRRRBBBBBBBBBBBGRR
BBBBBBRRRRRRRRRRBGGGGGGGGGGGGGGGGGGGGGGBBBBGGGGGGRRRRRRRRRRRRRBBBBBBBBBBBRR
GBBBBBRRRRRRRRRRRRRRRGGGGGGGGGGGGGGGGGGBBBBGGGGGGGRRRRRRRRRRRRRRRBBBBBBBBBB
GGBBBBBRRRRRRRRRRRRRRGGGGGGGGGGGGGGGGGGBBBBBGGGGGGGGRRRRRRRRRRRRRBBBBBBBBBB
RRBBBBBRRRRRRRRRRRRRRGGGGGGGGGGGGGGGGGGBBBBBBGGGGGGGRRRRRRRRRRRRRBBBBBBBBBB
RRBBBBBRRRRRRRRRRRRRRGGGGGGGGGGGGGGGGRGGGBBBBGGGGGGGGGGGGGGGRRRRRRBBBBBBBBB
RRBBBBBRRRRRRRRRRRRRRGGGGGGGGGGGGGGGGRGGGGBBBBGGGGGGGGGGGGGGGGGGGGBBBBBBBBB
RRRBBBBBRRRRRRRRRRRRRGGGGGBBBBBGGGGGGGGGGGGGGBBGGGGGGGGGGGGGGGGGGGGGRBBBBBB
RRRBBBBBBBRRRRRRRRRRRGGGGGGGGBBBGGGGGGGGGGGGGGBBGGGGGGGGGGGGGGGGGGGGRRRRRRR
GRRRRBRBBBRRRRRRRRRRRRRRRRGGGBBBGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGRR
GRRRRRRRRBRRRRRRRRRRRRRRRRGGGBBBBGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGRR
GRRRRRRRRRRRRRRRRRRRRRRRRRRRRBBBBGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGRR
GRRRRRRRRRRRRRRRRRGGGRRRRRRRRBBBBBGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGRR
GGGRRRRRRRRRRRRRRRGGGRRRRRRRRRBBBBBGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGRR
GGGRRRRRRRRRRRRRRRGGGRRRRRRRRRRBBBBBGGGGGBBGGRRRRGGGGGGGGGGGGGGGGGGGGGGGGGR
GGGRRRRRRRRRRRRRRRRGGRRRRRRRRRRBBBBRGGGGGGBGGRRRRGGGGGGGGGGGGGGGGGGGGGGGGRR
GGGRRRRRRRRRRRRRRRBBGGRRRRRRRRRBBBBRRRGGGGGGGRRRRGGGGGGGGGGGGGGGGGGGGGGGGGR
RGGGGGRRRRRRRRRRRRBBGGGGGGGRRRRBGBBRRRRRGGGGGRRRRGGGGGGGGGGGGBGGGGGGGGGGGGR
RGGGGGRRRRRRRRRRRRRRGGGGGGGRRRRRGBBBRRRRRGGGGBBBBBGGGGGGGGGGGGGGGGGGGRRGGGG
GGRGGGRRRRRRRRRRRRRRGGBGGGGRRGGRGGGBRGRRRRGGGGBBBBGGGGGGGGGGGGGGGGGGGRRRGGG
GGRRRRRRRRGRRRRRRRRRRGBGGGGBBGGRGGGBRRRRRRGGGGBBBBBGGGGGGGGGGGGGGGGGGGRRGGG
GGRRRRRRRRGGRRRRRRRRRGBBBGGGGGGGGGRRRRRRRRGGGGBBBBBGGGGGGGGGGGGGGGGGGGGGGGG
GGGRRRRRRRRRRRRRRRRRRGGGGGGGGGGGBBRRRRRRRRGGGGBBBBBGGGGGGGGGRRGGGGGGGGGGGGG
GGGRRRRRRRRRRRRRRRRRRRGGGGGGGGGGGBRRRRRRRRRGGGGGBBBBBGGGGGGGRRRRGGGGGGBBBBG
GGGGGGRRRRRRRRRRRRRRRRRRGGGGGGGGGGRRRRRRRRRRGGGGBBBBBGGGRRGGRRRRRGGGGGGGGBG
GGGGGGGGGGRRRRRRRRRRRRRRRGGGGRGGGGRRRRRRRRRRGGGGBBBBBBGGRRRGRRRRRRRGGGGGGBG
GGGGGGGGGGRRRRRRRRRRRRRRRRRRRRRGGGGRRRRRRRRRRGGGGGGGBBGGRRRRRRRRRRRRRRGGGBB
GGGGGGGGGGGGRRRRRRRRRRRRRRRRRRRGGGGRRRBRRRRRRRRGGGGGGGGGGRRRRRRRRRRRRRGGGGB
BBBBGGGGGGGGRRRRRRRRRRRRRRRRRRRRGGGGRRRRBBBBRRRRRGGGGGGGGRRRRRRRRRRRBRGGGGB
BBBBGGGGGGGGRRRRRRRRRRRRRRRRRRRRGGGGGGGGGGBBRRRRRGGGGGGGGRRRRRRRRRRRRRGGGGG
RRRBGGGGGGGGRRRRRRRRRRRRRRRRRRRRGGGGGBGGGGBBBRRRRRGGGGGGGRRRRRRRRRRRRRGGGGG
BRRRGGGGGGGGRRRRRRRRRRRGGGGGGRRRGGGGGBGGGGGBBBRRBBGGGGGGGRRRRRRRRRRRGRRGGGG
GGRRGGGGGGGGGRRRRRRRRRRGGGGGGRRRGGGGGGGRRGGBBBRRRBBBGGGGGRRRRRRRRRRRRRRGGGG
GGRRGGGGGGGGGGRRRRRRRRRGGGGGGRRRGGGGGGGGGGGGGBBBRBBBGGGGGGGGGRRRRRRRRRRGGGG
GGGRRRGGGGGGGGRRRRRRRRRGGGGRRRRRGGGGGGGGGGGGGBBBRRRRGGGGGGGGGRRRRRRRRRRGGGG
GGGRRRGGGGGGGGRRRRRRRRRRRGGRRRRRBGGGGGGGGGGGGGGBBBRRGGGGGGGGGGGRRRRGGRRRGRG
GGGGRRRRGGGGGGRRRRRRRRRRRGGRRRRRBGGGGGGGGGGGGGGGGBBBBGGGGGGGGGGGRRRRGRRRRRG
GGGGGGRRRGGGGGRRRRRRRRRRRGGGGGGGBBBGGGGGGGGRGGGGGBBBBBGGGGGGGGGGGGRRGRRRRRR
GGGGGGRRRRGGGGGRRRRRRRRRRGGGGGGGBGBGGGGGGGGGGGGGRBBBBBBBBBGGGGGGGGGGGGRRRRR
GGGGGGRRRRRRRRRRRRRRRRRRRGGGRRGGBBBBBGGGGGGGGGGGRRRBBBBBBBBBGGGGGGGGGGGGRRR
GGGGGGGGGRRGRRRRRRRRRGRRRRGGRRRRBBBBBBBBBBGGGGGGRRRRBBBBBBBBBBBGGGGGGGGGRRR
GGGGGGGGBRRGRRRRRRRRRRRRRRGGRRRRRBBBBBBBBBBBGGGGRRRRRBBBBBBBBBBGGGGGGGGGGRR
BBGGGGGGGGGGGRRRRRRRRGRRRRRGGGGRRRRRBBBBBBBBBBBGRRRRRRBBBBBBBBBGGGGGGGGGGGR
BBGGGGGGGGGGGRRRRRRRRGGGRRRRGGGRRRRRBRRRBBBBBBBBBRRRRRRBGGGGGBBGGGGGGGGGGGR

ans: 97 48

 */