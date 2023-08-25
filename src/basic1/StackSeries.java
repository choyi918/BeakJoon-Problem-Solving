package basic1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * https://www.acmicpc.net/problem/1874
 * 풀이 :
 * 1. 변수 n을 만들어 스택에 넣은 수를 카운트함 n=0로 초기화하고 1부터 스택에 넣어간다.
 * 2. 뽑아야 되는 수 x가 n보다 크면 n = x 일 때까지 스택에 수를 계속해서 push.
 * 3. n = x가 되는 순간 pop 해서 출력
 * 4-1. x가 n보다 작다면 스택에 그 수가 있으므로 pop한다. -> pop한 수가 x라면 출력, x가 아니라면 수열을 만들 수 없으므로 "NO"를 출력하고 종료
 * 4-2. x가 n보다 크다면 x = n이 될 때까지 스택에 수를 계속해서 push.
 * 이후 원하는 결과가 나올 때 까지 2 ~ 4를 반복
 */
public class StackSeries {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int lineCount = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        LinkedList<Integer> stack = new LinkedList<>();
        int n = 0;

        for (int i = 0; i < lineCount; i++) {
            int x = Integer.parseInt(br.readLine());

            if (n < x) {
                while (n < x) {
                    stack.push(++n);
                    sb.append("+\n");
                }
            }

            Integer popped = stack.pop();
            if (popped == x) {
                sb.append("-\n");
            } else {
                sb = new StringBuilder();
                sb.append("NO");
                break;
            }
        }

        System.out.println(sb);

        br.close();
        isr.close();
    }
}
