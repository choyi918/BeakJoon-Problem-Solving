package BarkingDog.Hexa09;

import java.io.*;
import java.util.StringTokenizer;

public class AppliedTermProject {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        int testCaseCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCaseCount; i++) {
            int arrLength = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[arrLength];
            for (int j = 0; j < arrLength; j++)
                arr[j] = Integer.parseInt(st.nextToken());

//            int result = NxN(arr);
            int result = N(arr);
//            int result = newN(arr);

            bw.write(String.valueOf(result) + "\n");
        }

//        System.out.println("\nans:");
        bw.flush();
    }

    // barking dog resource
    private static int newN(final int[] arr) {
        final int NOT_VISITED = 0;
        final int VISITED = 1;
        final int CYCLE_IN = 2;
        final int NOT_CYCLE_IN = 3;

        int[] state = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int cur = i;

            if (state[cur] != NOT_VISITED)
                continue;

            while (true) {
                state[cur] = VISITED;
                cur = arr[cur] - 1;

                if (state[cur] == CYCLE_IN || state[cur] == NOT_CYCLE_IN) {
                    cur = i;
                    while (state[cur] == VISITED) {
                        state[cur] = NOT_CYCLE_IN;
                        cur = arr[cur] - 1;
                    }

                    break;
                }


                if (state[cur] == VISITED && cur != i) {
                    while (state[cur] != CYCLE_IN) {
                        state[cur] = CYCLE_IN;
                        cur = arr[cur] - 1;
                    }
                    cur = i;
                    while (state[cur] != CYCLE_IN) {
                        state[cur] = NOT_CYCLE_IN;
                        cur = arr[cur] - 1;
                    }
                    break;
                }

                if (state[cur] == VISITED && cur == i) {
                    while (state[cur] != CYCLE_IN) {
                        state[cur] = CYCLE_IN;
                        cur = arr[cur] - 1;
                    }

                    break;
                }
            }

        }

        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            if (state[i] == NOT_CYCLE_IN) result++;
        }

        return result;
    }

    private static int N(final int[] arr) {
        boolean[] visited = new boolean[arr.length];
        boolean[] inCycle = new boolean[arr.length];

        // N
        for (int i = 0; i < arr.length; i++) {
            int present = i;

            // 이미 방문 했다면 스킵
            if (visited[present])
                continue;

            // 해당 학생이 지목한 학생을 뽑음
            int next = arr[present] - 1;

            // 지목된 학생에게 이미 방문했다면 현재 학생은 싸이클에 속하지 않는 것임
            // 이미 방문처리가 되었다는 것은 이미 형성된 싸이클에 속한 학생이고, 현재학생은 싸이클 밖에 있는 학생임.
            if (visited[next]) {
                inCycle[present] = false;
                visited[present] = true;
                continue;
            }

            visited[present] = true;
            // 지목된 학생에서부터 계속해서 지목된 학생들을 방문함. 방문하다보면 이미 방문한 학생을 만나게 됨 그때 루프를 종료함
            while (!visited[next]) {
                visited[next] = true;
                next = arr[next] - 1;
            }

            if (next == present) {
                // 이미 방문한 학생이 현재 학생과 같다면 현재 학생은 싸이클에 속한 것임
                // 현재 학생부터 다시 지목된 학생들을 계속해서 방문해서 (다시 현재학생을 만날 때까지)싸이클에 속한 학생으로 처리함
                inCycle[present] = true;
                int inCycleFactor = arr[present] - 1;
                while (inCycleFactor != next) {
                    inCycle[inCycleFactor] = true;
                    inCycleFactor = arr[inCycleFactor] - 1;
                }
            } else {
                // 이미 방문한 학생이 현재 학생과 다르다면 현재 학생은 싸이클에 속하지 않음
                // 따라서 현재 학생부터 이미 방문한 학생을 지목한 학생(방문한 학생 직전 학생)까지 다시 방문하면서 모두 싸이클에 속하지 않다고 처리함
                int notInCycleFactor = present;
                while (notInCycleFactor != next) {
                    inCycle[notInCycleFactor] = false;
                    notInCycleFactor = arr[notInCycleFactor] - 1;
                }

                // 이미 방문한 학생부터 이 학생을 다시 만날때까지 지목된 학생을 다시 방문하여 싸이클에 속한 학생으로 처리함
                int inCycleFactor = next;
                inCycle[inCycleFactor] = true;
                inCycleFactor = arr[inCycleFactor] - 1;
                while (inCycleFactor != next) {
                    inCycle[inCycleFactor] = true;
                    inCycleFactor = arr[inCycleFactor] - 1;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            if (inCycle[i] == false)
                result++;
        }

        return result;
    }

    private static int NTest(final int[] arr) {
        boolean[] visited = new boolean[arr.length];
        boolean[] inCycle = new boolean[arr.length];

        System.out.println("----------------");
        // N
        for (int i = 0; i < arr.length; i++) {
            int present = i;

            // 이미 방문 했다면 스킵
            if (visited[present])
                continue;

            // 해당 학생이 지목한 학생을 뽑음
            int next = arr[present] - 1;

            // 지목된 학생에게 이미 방문했다면 현재 학생은 싸이클에 속하지 않는 것임
            // 이미 방문처리가 되었다는 것은 이미 형성된 싸이클에 속한 학생이고, 현재학생은 싸이클 밖에 있는 학생임.
            if (visited[next]) {
                inCycle[present] = false;
                visited[present] = true;
                System.out.println(present + 1);
                continue;
            }

            visited[present] = true;
            System.out.println(present + 1);
            // 지목된 학생에서부터 계속해서 지목된 학생들을 방문함. 방문하다보면 이미 방문한 학생을 만나게 됨 그때 루프를 종료함
            while (!visited[next]) {
                visited[next] = true;
                System.out.println(next + 1);
                next = arr[next] - 1;
            }

            if (next == present) {
                // 이미 방문한 학생이 현재 학생과 같다면 현재 학생은 싸이클에 속한 것임
                // 현재 학생부터 다시 지목된 학생들을 계속해서 방문해서 (다시 현재학생을 만날 때까지)싸이클에 속한 학생으로 처리함
                inCycle[present] = true;
                System.out.println(present + 1);
                int inCycleFactor = arr[present] - 1;
                while (inCycleFactor != next) {
                    inCycle[inCycleFactor] = true;
                    System.out.println(inCycleFactor + 1);
                    inCycleFactor = arr[inCycleFactor] - 1;
                }
            } else {
                // 이미 방문한 학생이 현재 학생과 다르다면 현재 학생은 싸이클에 속하지 않음
                // 따라서 현재 학생부터 이미 방문한 학생을 지목한 학생(방문한 학생 직전 학생)까지 다시 방문하면서 모두 싸이클에 속하지 않다고 처리함
                int notInCycleFactor = present;
                while (notInCycleFactor != next) {
                    inCycle[notInCycleFactor] = false;
                    System.out.println(notInCycleFactor + 1);
                    notInCycleFactor = arr[notInCycleFactor] - 1;
                }

                // 이미 방문한 학생부터 이 학생을 다시 만날때까지 지목된 학생을 다시 방문하여 싸이클에 속한 학생으로 처리함
                int inCycleFactor = next;
                inCycle[inCycleFactor] = true;
                System.out.println(inCycleFactor + 1);
                inCycleFactor = arr[inCycleFactor] - 1;
                while (inCycleFactor != next) {
                    inCycle[inCycleFactor] = true;
                    System.out.println(inCycleFactor + 1);
                    inCycleFactor = arr[inCycleFactor] - 1;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            if (inCycle[i] == false)
                result++;
        }

        return result;
    }

    // 최악의 경우 N * N 이구나..
    private static int ThinkNButNxN(final int[] arr) {
        int[] inCycle = new int[arr.length]; // 0: 탐색 안함, 1: not in Cycle, 2: in Cycle

        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            int present = i;

            int next = arr[present] - 1;

            if (arr[present] == 0 && inCycle[next] != 0) { // present is not Cycle
                inCycle[present] = 1;
                result++;
                continue;
            }

            for (int j = 0; j < arr.length; j++) {
                if (next == present) {
                    int cycleStartFactor = next;
                    inCycle[cycleStartFactor] = 2;

                    int nextCycleFactor = arr[cycleStartFactor] - 1;
                    while (cycleStartFactor != nextCycleFactor) {
                        inCycle[nextCycleFactor] = 2;
                        nextCycleFactor = arr[nextCycleFactor] - 1;
                    }

                    break;
                }

                next = arr[next] - 1;
            }

            if (inCycle[present] == 0) {
                inCycle[present] = 1;
                result++;
            }
        }

//        System.out.println();
//        for (int i = 0; i < inCycle.length; i++) {
//            System.out.print(inCycle[i] + " ");
//        }
//        System.out.println();

        return result;
    }

    private static int NxN(final int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            int nextFactor = arr[i];

            boolean isCycle = false;
            for (int j = 0; j < arr.length; j++) {
                int factor = arr[nextFactor - 1];

                if ((factor - 1) == i) { // -1을 해주는 이유 i는 0부터 시작이기 때문에
                    isCycle = true;
                    break;
                }

                nextFactor = factor;
            }

            if (!isCycle)
                result++;
        }

        return result;
    }
}

/*
    test case

2
7
3 1 3 7 3 4 6
8
1 2 3 4 5 6 7 8

ans:
3
0

1
7
2 3 4 5 3 1 2

ans:
4

1
10
2 3 4 5 6 7 8 9 10 10

ans:9

1
5
2 3 4 5 3

ans:
2

1
7
2 3 4 2 6 7 5

ans: 1

1
10
2 3 4 5 6 7 8 9 10 1

ans: 0

 */
