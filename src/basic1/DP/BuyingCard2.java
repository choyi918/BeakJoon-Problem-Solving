package basic1.DP;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BuyingCard2 {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int n = Integer.parseInt(br.readLine());

        List<Integer> cardPackPriceList = new ArrayList<>();
        String[] pricesString = br.readLine().split(" ");
        for (String priceString : pricesString) {
            cardPackPriceList.add(Integer.parseInt(priceString));
        }

//        int result = solveByBottomUp(n, cardPackPriceList);
        int result = solveByTopDown(n, cardPackPriceList);

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        bw.write(String.valueOf(result));
        bw.flush();

        isr.close();
        br.close();
        osw.close();
        bw.close();
    }

    private static int solveByTopDown(final int n, final List<Integer> prices) {
        int[] dp = new int[n + 1];
        return recInTopDown(n, dp, prices);
    }

    private static int recInTopDown(final int n, final int[] dp, final List<Integer> prices) {
        if (n == 0)
            return 0;

        if (dp[n] == 0)
            dp[n] = Integer.MAX_VALUE;

        if (dp[n] != Integer.MAX_VALUE)
            return dp[n];

        for (int i = 1; i <= n; i++) {
            dp[n] = Math.min(dp[n], recInTopDown(n - i, dp, prices) + prices.get(i - 1));
        }

        return dp[n];
    }

    private static int solveByBottomUp(final int n, final List<Integer> prices) {
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            if (dp[i] == 0)
                dp[i] = Integer.MAX_VALUE;

            for (int j = 1; j <= i; j++) {

                dp[i] = Math.min(dp[i], dp[i - j] + prices.get(j - 1));
            }
        }

        return dp[n];
    }
}
