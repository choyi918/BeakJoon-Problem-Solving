package basic1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ReversedString {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        StringBuilder sb = new StringBuilder();

        int lineCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < lineCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            while (st.hasMoreTokens()) {
                String token = st.nextToken();

                for (int j = 0; j < token.length(); j++) {
                    sb.append(token.charAt(token.length() - 1 - j));
                }

                sb.append(" ");
            }

            sb.append(System.lineSeparator());
        }

        System.out.println(sb);

        isr.close();
        br.close();
    }

}
