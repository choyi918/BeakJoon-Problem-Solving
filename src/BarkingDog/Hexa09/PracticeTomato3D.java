package BarkingDog.Hexa09;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PracticeTomato3D {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        {
            int[] dx = {-1, 0, 1, 0, 0, 0};
            int[] dy = {0, -1, 0, 1, 0, 0};
            int[] dz = {0, 0, 0, 0, 1, -1};

            StringTokenizer boardSpec = new StringTokenizer(br.readLine());

            int ySize = Integer.parseInt(boardSpec.nextToken());
            int xSize = Integer.parseInt(boardSpec.nextToken());
            int zSize = Integer.parseInt(boardSpec.nextToken());

            int[][][] board = new int[xSize][ySize][zSize];
            int[][][] distance = new int[xSize][ySize][zSize];

            Queue<Pos> queue = new LinkedList<>();

            for (int z = 0; z < zSize; z++) {
                for (int x = 0; x < xSize; x++) {
                    StringTokenizer boardLine = new StringTokenizer(br.readLine());

                    for (int y = 0; y < ySize; y++) {
                        int tomato = Integer.parseInt(boardLine.nextToken());

                        if (tomato == 0)
                            distance[x][y][z] = -1;
                        else if (tomato == 1)
                            queue.add(new Pos(x, y, z));

                        board[x][y][z] = tomato;
                    }
                }
            }


            while (queue.isEmpty() == false) {
                Pos popped = queue.poll();

                for (int i = 0; i < 6; i++) {
                    int nx = popped.x + dx[i];
                    int ny = popped.y + dy[i];
                    int nz = popped.z + dz[i];

                    if (nx < 0 || nx >= xSize)
                        continue;

                    if (ny < 0 || ny >= ySize)
                        continue;

                    if (nz < 0 || nz >= zSize)
                        continue;

                    /*
                        if (distance[nx][ny][nz] > 0)
                            continue;

                        if (board[nx][ny][nz] != 0)
                            continue;

                        이 주석 코드를 바로 밑 if문으로 바꿈으로써 board 2차원 배열을 사용하지 않고
                        distance 2차원 배열만 사용해 풀이가능 이 배열에 들어있는 원소가 '-1'인지 확인하면
                        그 위치에 안익은 토마토가 있다는 것을 확인이 가능하다.
                     */
                    if (distance[nx][ny][nz] > -1)
                        continue;

                    distance[nx][ny][nz] = distance[popped.x][popped.y][popped.z] + 1;
                    queue.add(new Pos(nx, ny, nz));
                }
            }

            int longest = 0;
            boolean isNotPerfect = false;
            for (int z = 0; z < zSize; z++) {
                for (int x = 0; x < xSize; x++) {
                    for (int y = 0; y < ySize; y++) {
                        if (distance[x][y][z] == -1)
                            isNotPerfect = true;

                        if (longest < distance[x][y][z])
                            longest = distance[x][y][z];

                         // System.out.print(distance[x][y][z] + " ");
                    }
                     // System.out.println();
                }
            }

            if (isNotPerfect)
                bw.write(String.valueOf(-1));
            else
                bw.write(String.valueOf(longest));

            bw.flush();
        }

    }

    static class Pos {
        int x;
        int y;
        int z;

        public Pos(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
