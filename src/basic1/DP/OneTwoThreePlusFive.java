package basic1.DP;

import java.io.*;

/*
    https://www.acmicpc.net/problem/15990
 */

public class OneTwoThreePlusFive {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        int testcaseCount = Integer.parseInt(br.readLine());

        int[] testcase = new int[testcaseCount];
        for (int i = 0; i < testcaseCount; i++) {
            testcase[i] = Integer.parseInt(br.readLine());
        }

        int maximumN = 100000;
        int[][] dp = new int[maximumN + 1][4]; // dp[조합해서 만들어야하는 수][조합에서 마지막 자리에 위치한 수]
        solveByRecursive(maximumN, dp);

        for (int i = 0; i < testcaseCount; i++) {
            int result = (dp[testcase[i]][1] + dp[testcase[i]][2] + dp[testcase[i]][3]) % 1000000009;
            bw.write(String.valueOf(result));
            bw.write('\n');
        }

        bw.flush();

        isr.close();
        osw.close();
        br.close();
        bw.close();
    }

    private static void solveByRecursive(final int n, final int[][] dp) {

        dp[1][1] = 1;
        dp[2][1] = 0;
        dp[3][1] = 1;

        dp[1][2] = 0;
        dp[2][2] = 1;
        dp[3][2] = 1;

        dp[1][3] = 0;
        dp[2][3] = 0;
        dp[3][3] = 1;

        for (int x = 1; x <= 3; x++) {
            dp[n][x] = rec(dp, n, x);
        }

//        return (dp[n][1] + dp[n][2] + dp[n][3]) % 1000000009;
    }

    private static int rec(int[][] dp, int n, int po) {
        if (((1 <= n && n <= 3) && (1 <= po && po <= 3)) || (dp[n][po] != 0)) {
            return dp[n][po];
        }

        if (n - 1 >= 0)
            dp[n][1] = (rec(dp, n - 1, 2) + rec(dp, n - 1, 3)) % 1000000009;
        if (n - 2 >= 0)
            dp[n][2] = (rec(dp, n - 2, 1) + rec(dp, n - 2, 3)) % 1000000009;
        if (n - 3 >= 0)
            dp[n][3] = (rec(dp, n - 3, 1) + rec(dp, n - 3, 2)) % 1000000009;

        return dp[n][po];
    }
}
