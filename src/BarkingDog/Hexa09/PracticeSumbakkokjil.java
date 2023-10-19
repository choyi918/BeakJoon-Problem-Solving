package BarkingDog.Hexa09;

import java.io.*;
import java.util.*;

public class PracticeSumbakkokjil {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        {
            StringTokenizer nm = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(nm.nextToken()); // 수빈이 위치
            int m = Integer.parseInt(nm.nextToken()); // 동생 위치

            if (n == m) {
                bw.write(String.valueOf(0));
                bw.flush();
                return;
            }


            int[] distance = new int[100001];

            Queue<Integer> queue = new LinkedList();

            queue.add(n);

            int result = 0;

            while(queue.isEmpty() == false) {
                Integer popped = queue.poll();

                if (popped - 1 != n && popped - 1 > -1 && distance[popped - 1] == 0) {
                    distance[popped - 1] = distance[popped] + 1;
                    queue.add(popped - 1);

                    if (popped - 1 == m) {
                        result = distance[popped - 1];
                        break;
                    }
                }

                if (popped + 1 != n && popped + 1 < 100001 && distance[popped + 1] == 0) {
                    distance[popped + 1] = distance[popped] + 1;
                    queue.add(popped + 1);

                    if (popped + 1 == m) {
                        result = distance[popped + 1];
                        break;
                    }
                }

                if (popped * 2 != n && popped * 2 < 100001 && distance[popped * 2] == 0) {
                    distance[popped * 2] = distance[popped] + 1;
                    queue.add(popped * 2);

                    if (popped * 2 == m) {
                        result = distance[popped * 2];
                        break;
                    }
                }
            }

            bw.write(String.valueOf(result));
            bw.flush();
        }
    }
}

/*
    test case

0 0

ans: 1

100000 99999

ans: 1


 */
