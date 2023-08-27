package basic1;

import java.io.*;
import java.util.LinkedList;

/**
 * https://www.acmicpc.net/problem/17413
 */

public class ReversedString2 {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        StringBuilder sb = new StringBuilder();

        String str = br.readLine();

        LinkedList<Character> buff = new LinkedList<>();
        boolean onSkip = false;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == '<') {
                onSkip = true;
            }

            if (ch == '>') {
                onSkip = false;
            }

            if (ch == '<' || (ch == ' ' && !onSkip)) {
                while (!buff.isEmpty())
                    sb.append(buff.removeLast());

                if (ch == ' ')
                    sb.append(" ");
            }

            if (onSkip || ch == '>') {
                sb.append(ch);
            } else {
                if (ch != ' ')
                    buff.addLast(ch);
            }
        }

        /* 마지막 문자열 뒤집기 */
        while (!buff.isEmpty())
            sb.append(buff.removeLast());


        bw.write(sb.toString());
        bw.flush();

        isr.close();
        br.close();
        osw.close();
        bw.close();
    }
}
