package BarkingDog.Hexa09;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BasicSafeArea {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(line.nextToken());
            }
        }


        boolean isSameAll = true;
        int before = board[0][0];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (before != board[i][j]) {
                    isSameAll = false;
                }
            }
        }

        if (isSameAll) {
            bw.write(String.valueOf(1));
            bw.flush();
            return;
        }

        int result = solve(board);

        bw.write(String.valueOf(result));
        bw.flush();
    }

    private static int solve(final int[][] board) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        int maximumCount = 0;

        for (int h = 1; h <= 100; h++) {
            Queue<Pos> queue = new LinkedList<>();

            boolean[][] visited = new boolean[board.length][board.length];
            int countByh = 0;

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] <= h || visited[i][j])
                        continue;

                    queue.add(new Pos(i, j));
                    visited[i][j] = true;
                    countByh++;

                    while (queue.isEmpty() == false) {
                        Pos popped = queue.poll();

                        for (int k = 0; k < 4; k++) {
                            int nx = popped.x + dx[k];
                            int ny = popped.y + dy[k];

                            if (nx < 0 || nx >= board.length || ny < 0 || ny >= board.length)
                                continue;

                            if (visited[nx][ny] || board[nx][ny] <= h)
                                continue;

                            queue.add(new Pos(nx, ny));
                            visited[nx][ny] = true;
                        }
                    }
                }
            }

            if (maximumCount < countByh)
                maximumCount = countByh;
        }

        return maximumCount;
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
