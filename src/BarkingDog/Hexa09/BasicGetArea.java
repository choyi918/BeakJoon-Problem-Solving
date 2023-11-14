package BarkingDog.Hexa09;

import java.io.*;
import java.util.*;

public class BasicGetArea {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        StringTokenizer boardSpec = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(boardSpec.nextToken());
        int v = Integer.parseInt(boardSpec.nextToken());
        int k = Integer.parseInt(boardSpec.nextToken());

        int[][] board = new int[v][h];

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int v1 = v - Integer.parseInt(st.nextToken()) - 1;
//            System.out.println("v1 = " + v1);
            int y1 = Integer.parseInt(st.nextToken());
//            System.out.println("y1 = " + y1);
            int v2 = v - Integer.parseInt(st.nextToken());
//            System.out.println("v2 = " + v2);
            int y2 = Integer.parseInt(st.nextToken()) - 1;
//            System.out.println("y2 = " + y2);

            for (int j = v2; j <= v1; j++) {
                for (int e = y1; e <= y2; e++) {
                    board[j][e] = 1;
                }
            }
        }

//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0].length; j++) {
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }

        Queue<Pos> queue = new LinkedList<>();
        int[][] visited = new int[v][h];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        List<Integer> areas = new ArrayList<>();
        int areaCount = 0;
        for (int x = 0; x < v; x++) {
            for (int y = 0; y < h; y++) {
                if (visited[x][y] == 1 || board[x][y] == 1)
                    continue;

                queue.add(new Pos(x, y));
                visited[x][y] = 1;
                int size = 1;

                while (queue.isEmpty() == false) {
                    Pos popped = queue.poll();

                    for (int i = 0; i < 4; i++) {
                        int nx = popped.x + dx[i];
                        int ny = popped.y + dy[i];

                        if (nx < 0 || nx >= v || ny < 0 || ny >= h)
                            continue;

                        if (visited[nx][ny] == 1 || board[nx][ny] == 1)
                            continue;

                        queue.add(new Pos(nx, ny));
                        visited[nx][ny] = 1;
                        size++;
                    }
                }

                areas.add(size);
                areaCount++;
            }
        }

        bw.write(String.valueOf(areaCount) + '\n');

        Collections.sort(areas);
        for (int area : areas) {
            bw.write(area + " ");
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

    private void solve() {

    }
}
