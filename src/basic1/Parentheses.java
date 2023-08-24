package basic1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Parentheses {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        StringBuilder sb = new StringBuilder();

        int lineCount = Integer.parseInt(br.readLine());

        LinkedList<Character> stack = new LinkedList<>();

        for (int i = 0; i < lineCount; i++) {
            String parentheses = br.readLine();

            boolean isNo = false;
            for (int j = 0; j < parentheses.length(); j++) {
                char ch = parentheses.charAt(j);

                if (ch == '(') {
                    stack.push(ch);
                    continue;
                }

                if (stack.isEmpty()) {
                    isNo = true;
                    break;
                }

                stack.pop();
            }

            if (isNo || stack.isEmpty() == false) {
                sb.append("NO\n");
                stack.clear();
            } else {
                sb.append("YES\n");
            }
        }

        System.out.println(sb);

        isr.close();
        br.close();
    }
}
