package BarkingDog.Hexa0B;

import java.io.*;

public class PracticeTopOfHanoi {
    private static int minimumMovementCount = 0;

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        StringBuilder sb = new StringBuilder();
        solveRecursive(1, 3, 3, sb);

        bw.write(minimumMovementCount + "\n");
        bw.write(sb.toString());
        bw.flush();
    }

    public static void solveRecursive(int a, int b, int n, StringBuilder sb) {
        minimumMovementCount++;

        if (n == 1) {
            sb.append(a + " " + b + "\n");
            return;
        }

        solveRecursive(a, 6 - a - b, n - 1, sb);

        sb.append(a + " " + b + "\n");

        solveRecursive(6 - a - b, b, n - 1, sb);
    }
}
