package basic1;

import java.io.*;
import java.util.LinkedList;

/*
    https://www.acmicpc.net/problem/1918
 */
public class PostfixExpression2 {
    private static BufferedWriter bw;
    private static int nowIndex = 0;

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        bw = new BufferedWriter(osw);

        String infix = br.readLine();
        solve(infix);
        bw.flush();

        isr.close();
        osw.close();
        br.close();
        bw.close();
    }

    private static void solve(final String infix) throws IOException {
        /**
         * 풀이: 피연산자를 위한 스택을 선언한다.
         * 1. 피연산자는 발견할 때마다 출력한다.
         * 2. 연산자는 우선순위를 둔다.
         *  - stack에서 는 + 또는 -가 * 또는 / 보다 스택에서 위에 위치해 있는 순간 다 비워서 출력해야한다.
         *  - () 괄호는 스택에 들어가서 완성되는 순간 안쪽 연산자들은 바로 빼서 출력해야한다.
         */

        rec(infix);
    }

    private static void rec(final String infix) throws IOException {
    }
}
