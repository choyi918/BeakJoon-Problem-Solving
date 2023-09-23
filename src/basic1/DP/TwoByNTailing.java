package basic1.DP;

import java.io.*;
import java.util.HashMap;

/*
 * https://www.acmicpc.net/problem/11726
 * 1. 점화식을 세우는 문제
 * 2. n = 6 정도까지 경우의 수를 세다보면 피보나치 수열이라는 것을 알 수 있음
 */
public class TwoByNTailing {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int n = Integer.parseInt(br.readLine());

        int result = solve(n);
        result %= 10007;

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        bw.write(String.valueOf(result));
        bw.flush();

        isr.close();
        br.close();
        osw.close();
        bw.close();
    }

    private static int solve(final int n) {
//        return fibonacciByTopDown(n);
        return fibonacciByBottomUp(n);
    }

    private static int fibonacciByTopDown(final int rootNum) {
        int[] memo = new int[rootNum + 1];
        return recur(memo, rootNum);
    }

    private static int recur(final int[] memo, final int num) {
        if (memo[num] != 0)
            return memo[num];

        if (num == 1) {
            memo[num] = 1;
            return 1;
        }

        if (num == 2) {
            memo[num] = 2;
            return 2;
        }

        memo[num] = recur(memo, num - 1) + recur(memo, num - 2);
        memo[num] %= 10007;

        return memo[num];
    }

    private static int fibonacciByBottomUp(final int rootNum) {
        int[] tabulation = new int[rootNum + 1];
        tabulation[1] = 1;

        if (tabulation.length > 2)
            tabulation[2] = 2;

        for (int i = 3; i <= rootNum; i++) {
            tabulation[i] = tabulation[i - 1] + tabulation[i - 2];
            tabulation[i] %= 10007;
        }

        return tabulation[rootNum];
    }
}
