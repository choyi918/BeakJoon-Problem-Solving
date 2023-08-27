package basic1;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1158
 * 사용할 자료구조 : Queue by LinkedList
 * 풀이 :
 * 1. 요세푸스 순열의 점화식 구하기
 * 2. 요세푸스 순열 (N, K) = (7, 3) 일 때 <3, 6, 2, 7, 5, 1, 4>
 * 3. 카운트 변수 하나를 선언한다. cnt = 1;
 * 4. 루프를 한번 돌 때마다 cnt++하고,
 *  1) cnt = k 일 때 removeFirst하여 값을 하나 제거, cnt = 1로 초기화. 제거한 값은 출력
 *  2) cnt != k 일 때, removeFirst하여 다시 뒤에 addLast함
 */

public class JosephusProblem {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        String numberSetLine = br.readLine();
        StringTokenizer tokens = new StringTokenizer(numberSetLine, " ");

        int n = Integer.parseInt(tokens.nextToken());
        int k = Integer.parseInt(tokens.nextToken());

        String result = JosephusPermutation.getResult(n, k);

        bw.write(result);
        bw.flush();

        isr.close();
        br.close();
        osw.close();
        bw.close();
    }

    public static class JosephusPermutation {
        private static LinkedList<Integer> queue = new LinkedList<>();
        public static String getResult(final int n, final int k) {
            for (int i = 0; i < n; i++) {
                queue.addLast(i + 1);
            } // O(N)

            StringBuilder sb = new StringBuilder();

            sb.append("<");

            int cnt = 1;
            while (!queue.isEmpty()) {
                Integer popped = queue.removeFirst();

                if (cnt == k) {
                    cnt = 1;

                    sb.append(popped);

                    if (!queue.isEmpty()) {
                        sb.append(", ");
                    }
                } else {
                    cnt++;
                    queue.addLast(popped);
                }
            } // O(N^2) -> n개의 원소에서 n개를 한번 순회할 때마다 한개씩 제거하므로 N^2 / 2 (역피라미드 모양)

            sb.append(">");

            return sb.toString();
        }
    }
}
