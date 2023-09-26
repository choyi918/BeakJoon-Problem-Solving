package basic1;

import java.io.*;
import java.util.LinkedList;

public class Okunsoo {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        br.readLine();

        String[] arr = br.readLine().split(" ");

        String result = solve(arr);

        /**
         * 출력
         */
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        bw.write(result);
        bw.flush();

        isr.close();
        br.close();
        osw.close();
        bw.close();
    }

    private static String solve(String[] arr) {
        LinkedList<Integer> stack = new LinkedList<>();
        int[] result = new int[arr.length];
        // LinkedList<Integer> result = new LinkedList<>(); // 시간 초과 원인


        for (int i = arr.length - 1; i >= 0; i--) {
            int n = Integer.parseInt(arr[i]);

            while (true) {

                if (stack.isEmpty()) {
                    // result.addFirst(-1) // 시간 초과 원인
                    result[i] = -1;
                    stack.push(n);
                    break;
                }

                Integer popped = stack.pop();

                if (n < popped) {
                    // result.addFirst(popped) // 시간 초과 원인
                    result[i] = popped;
                    stack.push(popped);
                    stack.push(n);
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            // sb.append(result.get(i)) // 시간 초과 원인
            sb.append(result[i]);

            if (i != result.length - 1)
                sb.append(" ");
        }

        return sb.toString();
    }
}
