package basic1.DP;

import java.io.*;

public class TwoByNTailing2 {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int n = Integer.parseInt(br.readLine());

        int result = solve(n);

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        bw.write(String.valueOf(result));
        bw.flush();

        isr.close();
        bw.close();
        osw.close();
        br.close();
    }

    private static int solve(final int n) {
        // f(n) = f(n - 1) + 2f(n - 2)

        int[] f = new int[n + 1];

        f[1] = 1;

        if (n >= 2)
            f[2] = 3;

        for (int i = 3; i <= n; i++) {
            f[i] = f[i - 1] + 2 * f[i - 2];
            f[i] %= 10007;
        }

        return f[n];
    }
}
