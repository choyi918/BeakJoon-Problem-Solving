package BarkingDog.Hexa09;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BasicNumberingApartmentComplex {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        int n = Integer.parseInt(br.readLine());

        int[][] board = new int[n][n];
        int[][] visited = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] boardLine = br.readLine().split("");

            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(boardLine[j]);
            }
        }

//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0].length; j++) {
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }

        Queue<Pos> queue = new LinkedList<>();

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        int complexCount = 0;
        ArrayList<Integer> sizes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == 1 || board[i][j] == 0)
                    continue;

                queue.add(new Pos(i, j));
                visited[i][j] = 1;
                int size = 1;
                complexCount++;

                while(queue.isEmpty() == false) {
                    Pos popped = queue.poll();

                    for (int k = 0; k < 4; k++) {
                        int nx = popped.x + dx[k];
                        int ny = popped.y + dy[k];

                        if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                            continue;

                        if (visited[nx][ny] == 1 || board[nx][ny] == 0)
                            continue;

                        queue.add(new Pos(nx, ny));
                        visited[nx][ny] = 1;
                        size++;
                    }
                }

                sizes.add(size);
            }
        }

        bw.write(String.valueOf(complexCount) + '\n');
        Collections.sort(sizes);
        for (int size : sizes) {
            bw.write(String.valueOf(size) + '\n');
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
