package basic1.DP;

import java.io.*;

/*
 https://www.acmicpc.net/problem/9095
 */
public class OneTwoThreePlus {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        int tcCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < tcCount; i++) {
            int num = Integer.parseInt(br.readLine());
            int result = solve(num);

            bw.write(String.valueOf(result));
            bw.write("\n");
        }

        bw.flush();

        isr.close();
        br.close();
        osw.close();
        bw.close();
    }

    private static int solve(final int num) {
        int[] f = new int[num + 1];

        f[1] = 1;

        if (num >= 2) {
            f[2] = 2;
        }

        if (num >= 3) {
            f[3] = 4;
        }

        for (int i = 4; i <= num; i++) {
            f[i] = f[i - 1] + f[i - 2] + f[i - 3];
        }

        return f[num];
    }
}
