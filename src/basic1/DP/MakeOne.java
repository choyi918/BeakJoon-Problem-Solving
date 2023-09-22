package basic1.DP;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;

/*
    https://www.acmicpc.net/problem/1463
 */

public class MakeOne {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int num = Integer.parseInt(br.readLine());

//        int result = solveByBottomUp(num);
//        int result = solveByTopDown(num);
        int result = solveByBFS(num);

        OutputStreamWriter osw = new OutputStreamWriter(System.out, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);

        bw.write(String.valueOf(result));
        bw.flush();

        isr.close();
        br.close();
        osw.close();
        bw.close();
    }

    private static int solveByBFS(final int num) {
        /*
         * BFS 최단거리 찾기 방식의 문제 풀이
         * 1. Root 노드를 num으로 하는 트리의 BFS 탐색을한다.
         * 2. 각 노드의 child 개수는 최대 3개이다 (parent - 1, if parent % 2 == 0, if parent % 3 == 0)
         * 3. Queue를 선언하여 BFS 탐색을 한다. 이때 방문한 노드가 루트까지의 최소 연산횟수를 기억하는 visited 배열을 선언한다.
         * 4.
         */

        int[] visited = new int[num + 1];
        visited[num] = 0;

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(num);

        int n = Integer.MAX_VALUE;
        while (n > 1) {
            n = queue.removeFirst();

            if (visited[n - 1] == 0) {
                visited[n - 1] = visited[n] + 1;
                queue.addLast(n - 1);
            }

            if (n % 2 == 0 && visited[n / 2] == 0) {
                visited[n / 2] = visited[n] + 1;
                queue.addLast(n / 2);
            }

            if (n % 3 == 0 && visited[n / 3] == 0) {
                visited[n / 3] = visited[n] + 1;
                queue.addLast(n / 3);
            }
        }

        return visited[1];
    }

    private static int solveByBottomUp(final int num) {
        /*
         * Bottom-Up 방식의 풀이
         * 1. i = [2, Num]이고, 오름차순으로 순회한다. 길이가 num인 dp 배열을 선언한다.
         * 2. i가 1이 되기 위한 연산을 계산하고 비교하여 최솟값을 메모리에 저장한다.
         *   [1] -1 연산을 시행이 가능할 때, dp[i - 1]의 연산 횟수 + 1을 dp[i]에 저장한다. (여기서 dp[i - 1]은 이전 부분 시행에서 구한 최소값이다)
         *   [2] 2로 나누기 연산이 시행 가능할 때, [i / 2]의 연산 횟수 + 1을 [1]번에서 시행한 결과인 dp[i]과 비교한 후 최소값을 선택한다. (여기서 dp[i / 2]는 이전 부분 시행에서 구한 최소값이다)
         *   [3] 3으로 나누기 연산이 시행 가능할 때, [i / 3]의 연산 횟수 + 1을 [2]번까지 시행한 결과인 dp[i]과 비교한 후 최값을 선택한다. (여기서 dp[i / 3]는 이전 부분 시행에서 구한 최소값이다)
         * 3. i = Num이 되어 dp[num]을 구할 때까지 2번을 반복시행한다.
         *
         * ** 근데 바텀업 방식은 타뷸레이션 테크닉을 사용하고, 이는 직전 시행한 결과만 메모리에 기억해놓고 사용하는 것인데, 전체 배열을 사용하지 않고 푸는 방법도 있지 않을까?
         */

        int[] dp = new int[num + 1];
        dp[1] = 0; // 1은 1이되기 위한 연산 0번시행

        for (int i = 2; i <= num; i++) {
            dp[i] = dp[i - 1] + 1;

            if (i % 2 == 0)
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);

            if (i % 3 == 0)
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
        }

        return dp[num];
    }

    private static int solveByTopDown(final int num) {
        /*
         * Top-down 방식의 풀이
         * 1. DP 해시맵을 선언 (메모이제이션); key - 해당 num, value - 해당 num이 1이되기 위한 최소연산수
         * 2. Num부터 작은 수 방향으로 재귀호출
         */

        HashMap<Integer, Integer> dp = new HashMap<>();
        dp.put(1, 0);
        return recurByTopDown2(dp, num);
    }

    private static int recurByTopDown(HashMap<Integer, Integer> dp, final int num) {
        /*
         * 시간초과 된 풀이
         */

        if (dp.containsKey(num))
            return dp.get(num);

        int min = recurByTopDown(dp, num - 1) + 1;

        if (num % 2 == 0)
            min = Math.min(min, recurByTopDown(dp, num / 2) + 1);

        if (num % 3 == 0)
            min = Math.min(min, recurByTopDown(dp, num / 3) + 1);

        dp.put(num, min);

        return min;
    }

    private static int recurByTopDown2(HashMap<Integer, Integer> dp, final int num) {
        if (dp.containsKey(num))
            return dp.get(num);

        int min = Integer.MAX_VALUE;

        if (num % 2 == 0 && num % 3 == 0) {
            min = Math.min(recurByTopDown2(dp, num / 2) + 1, recurByTopDown2(dp, num / 3) + 1);
        } else if (num % 2 == 0) {
            min = Math.min(min, recurByTopDown2(dp, num / 2) + 1);
        } else if (num % 3 == 0) {
            min = Math.min(min, recurByTopDown2(dp, num / 3) + 1);
        }

        min = Math.min(min, recurByTopDown2(dp, num - 1) + 1);

        dp.put(num, min);

        return min;
    }

    private static int recurByTopDown3(HashMap<Integer, Integer> dp, final int num) {
        if (dp.containsKey(num))
            return dp.get(num);

        if (num % 3 == 0 && num % 2 == 0)
            dp.put(num, Math.min(recurByTopDown3(dp, num / 3) + 1, recurByTopDown3(dp, num / 2) + 1));
        else if (num % 3 == 0)
            dp.put(num, Math.min(recurByTopDown3(dp, num / 3) + 1, recurByTopDown3(dp, num - 1) + 1));
        else if (num % 2 == 0)
            dp.put(num, Math.min(recurByTopDown3(dp, num / 2) + 1, recurByTopDown3(dp, num - 1) + 1));
        else
            dp.put(num, recurByTopDown3(dp, num - 1) + 1);

        return dp.get(num);
    }
}
