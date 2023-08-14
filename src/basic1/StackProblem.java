package basic1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 Link : https://www.acmicpc.net/problem/10828
 */

public class StackProblem {
    public static void main(String args[]) throws IOException {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        StringBuilder sb = new StringBuilder();


        int lineCount = Integer.parseInt(br.readLine());

        Stack stack = new Stack();

       for (int i = 0; i < lineCount; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            String command = st.nextToken(); // push, top, pop, empty, size

            if (st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                stack.push(num);
                continue;
            }

            if (command.equals("top"))
                sb.append(stack.top());
            else if (command.equals("pop"))
                sb.append(stack.pop());
            else if (command.equals("empty"))
                sb.append(stack.empty());
            else // command.equals("size")
                sb.append(stack.size());

            sb.append(System.lineSeparator());
        }

        System.out.println(sb);

       isr.close();
       br.close();
    }

    public static class Stack {
        private int nums[] = new int[10000];
        private int size = 0;

        public void push(int num) {
            nums[size++] = num;
        }

        public int pop() {
            if (size == 0)
                return -1;

            return nums[size-- - 1];
        }

        public int size() {
            return size;
        }

        public int empty() {
            return size == 0 ? 1 : 0;
        }

        public int top() {
            return size == 0 ? -1 : nums[size - 1];
        }
    }
}
