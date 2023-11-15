package BarkingDog.Hexa09;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BasicStartLink {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int f = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int u = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[] distance = new int[f + 1];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        distance[s] = 1;

        while (queue.isEmpty() == false) {
            Integer present = queue.poll();

            int du = present + u;
            int dd = present - d;

            if (du == g) {
                distance[du] = distance[present] + 1;
                break;
            }

            if (dd == g) {
                distance[dd] = distance[present] + 1;
                break;
            }

            if (du <= f && distance[du] == 0) {
                queue.add(du);
                distance[du] = distance[present] + 1;
            }

            if (dd >= 1 && distance[dd] == 0) {
                queue.add(dd);
                distance[dd] = distance[present] + 1;
            }
        }

        if (distance[g] == 0) {
            bw.write("use the stairs");
        } else {
            bw.write(String.valueOf(distance[g] - 1));
        }
        bw.flush();
    }
}
