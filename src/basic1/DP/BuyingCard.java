package basic1.DP;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BuyingCard {
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
        HashMap<Integer, Integer> dp = new HashMap<>();

        dp.put(1, prices.get(0));
        return recurByTopDown(n, 1, prices, dp);
    }

    private static int recurByTopDown(final int n, final int depth, final List<Integer> prices, final HashMap<Integer, Integer> dp) {
        if (n - depth == 0)
            return 0;

        if (n - depth == 1)
            return dp.get(1);

        if (dp.containsKey(n)) {
            dp.put(n, Math.max(dp.get(n), recurByTopDown(n - (depth + 1), (depth + 1), prices, dp) + prices.get(depth)));
        } else {
            dp.put(n, recurByTopDown(n - (depth + 1), (depth + 1), prices, dp) + prices.get(depth));
        }

        return dp.get(n);
    }

    private static int solveByBottomUp(final int n, final List<Integer> prices) {
        /**
         * 잘못된 풀이 -> 하위 문제를 잘못 설정한 것 같다.
         * 잘못 된 하위 문제 설정 : prices[i]에 해당하는 카드팩을 최대한 산 뒤, 남은 장수에 대한 최대값을 가져와 더하는 방식으로 최댓값을 구했다.
         *
             int[] dp = new int[n + 1];

             for (int i = 0; i < prices.size(); i++) {
             for (int j = i + 1; j <= n; j++) {
             dp[j] = Math.max(dp[j], prices.get(i) * (j / (i + 1)) + dp[j % (i + 1)]);
             }
             }

             return dp[n];
         * 반례
         * 10
         * 1 100 160 1 1 1 1 1 1 1
         * 500
         */

        /*
         * 올바른 하위 문제 : i장짜리 카드팩 하나를 구매 했을 때 N - i 개의 카드팩을 구매할 때의 최대값
         * dp[n] = max(dp[n], dp[n - 1] + prices[1 - 1])
         * dp[n] = max(dp[n], dp[n - 2] + price[2 - 1])
         * dp[n] = max(dp[n], dp[n - 3] + price[3 - 1])
         *          ...
         */

        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                // dp[0](== 0)일 때는 카드팩i 만으로 가격을 채운다고 생각하면된다.
                dp[i] = Math.max(dp[i], dp[i - j] + prices.get(j - 1));
            }
        }

        return dp[n];
    }
}
