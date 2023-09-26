package basic1;

import java.io.*;
import java.util.LinkedList;

/*
    https://www.acmicpc.net/problem/1935
 */

public class PostfixExpression {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int operandCount = Integer.parseInt(br.readLine());

        double[] operands = new double[operandCount];

        String postfix = br.readLine();

        for (int i = 0; i < operandCount; i++)
            operands[i] = Integer.parseInt(br.readLine());

        double result = solve(postfix, operands);

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        bw.write(String.format("%.2f", result));
        bw.flush();

        isr.close();
        osw.close();
        br.close();
        bw.close();
    }

    private static double solve(final String postfix, final double[] operands) {
        LinkedList<Double> stack = new LinkedList<>();

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            if ('A' <= c && c <= 'Z') {
                int index = Character.valueOf(c) - 'A';
                stack.push(operands[index]);
            } else {
                Double operand1 = stack.pop();
                Double operand2 = stack.pop();

                if (c == '+')
                    stack.push(operand2 + operand1);
                else if (c == '-')
                    stack.push(operand2 - operand1);
                else if (c == '*')
                    stack.push(operand2 * operand1);
                else // c == '/'
                    stack.push(operand2 / operand1);


            }
        }

        return stack.pop();
    }
}
