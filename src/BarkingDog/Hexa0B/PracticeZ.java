package BarkingDog.Hexa0B;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class PracticeZ {
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

    }

    private static int recur(int n, int r, int c) {
        if (n == 2) {
            if (r == 0 && c == 0)
                return 0;
            else if (r == 0 && c == 1)
                return 1;
            else if (r == 1 && c == 0)
                return 2;
            else
                return 3;
        }

        int half = (n / 2) * (n / 2);

        return 0;
    }

}
