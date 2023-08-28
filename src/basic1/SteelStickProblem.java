package basic1;

import java.io.*;
import java.util.LinkedList;

/**
 * https://www.acmicpc.net/problem/10799
 * 풀이 :
 * 사용한 자료구조 : Stack by LinkedList
 * 1. 스택에 '('를 쌓는다.
 * 2. 레이저가 만들어지는 순간 스택에 쌓인 괄호의 개수대로 조각의 개수가 발생한다.
 * 3. 쇠막대기가 하나 끝나는 순간 (레이저를 만들지 않는 ')'가 등장하면 쇠막대기가 끝난다) 조각 개수가 하나 발생한다.
 * 4. 입력받은 괄호 배열이 끝날 때 까지 1 ~ 3을 반복한다.
 */

public class SteelStickProblem {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        String parentheses = br.readLine();

        int result = SteelSTickProblemSolver.getResult(parentheses);

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        bw.write(result);
        bw.flush();

        isr.close();
        br.close();
    }

    public static class SteelSTickProblemSolver {
        private static LinkedList<Character> stack = new LinkedList<>();

        public static int getResult(String parentheses) {
            int resultCount = 0;

            boolean razor = false;
            for (int i = 0; i < parentheses.length(); i++) {
                char paren = parentheses.charAt(i);

                if (paren == '(') {
                    stack.push(paren);
                    razor = false;
                } else {
                    stack.pop();

                    if (razor == false) {
                        razor = true;
                        resultCount += stack.size();
                    } else {
                        resultCount += 1;
                    }
                }
            }

            return resultCount;
        }
    }
}
