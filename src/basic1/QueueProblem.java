package basic1;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/10845
 * 풀이:
 *  1. RingBuffer를 구현하여 Queue로 사용한다.
 *  2. front, back 변수를 선언하여 링버퍼에서 Queue의 맨앞과 맨뒤를 표시한다.
 *  3. size 변수를 선언하여 Queue의 크기를 카운트한다.
 *  4. pop, push에 따라 링버퍼에 값을 넣거나 빼고, front, back, size를 조정한다.
 */

public class QueueProblem {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);
        StringBuilder sb = new StringBuilder();

        int lineCount = Integer.parseInt(br.readLine());

        Queue queue = new Queue();

        for (int i = 0; i < lineCount; i++) {
            String commandLine = br.readLine();

            StringTokenizer tokens = new StringTokenizer(commandLine, " ");

            String command = tokens.nextToken();

            if (command.equals("push")) {
                int value = Integer.parseInt(tokens.nextToken());
                queue.push(value);
                continue;
            }

            if (command.equals("pop")) {
                sb.append(queue.pop());
            } else if (command.equals("empty")) {
                sb.append(queue.empty());
            } else if (command.equals("size")) {
                sb.append(queue.size());
            } else if (command.equals("front")) {
                sb.append(queue.front());
            } else if (command.equals("back")) {
                sb.append(queue.back());
            }

            sb.append(System.lineSeparator());
        }


        /*
            출력
         */
        bw.write(sb.toString());
        bw.flush();

        osw.close();
        bw.close();
        isr.close();
        br.close();
    }

    public static class Queue {
        private static int BUFF_SIZE = 10000;
        private int[] nums = new int[BUFF_SIZE];
        private int front = 0;
        private int back = 0;
        private int size = 0;

        public void push(int value) {
            if (size == 0) {
                nums[back] = value;
            } else {
                back++;

                if (back == BUFF_SIZE) {
                    back = 0;
                }

                nums[back] = value;
            }

            size++;

            assert (0 <= front && front <= BUFF_SIZE && 0 <= back && back <= BUFF_SIZE);
        }

        public int pop() {
            if (size == 0) {
                return -1;
            }

            int popped = nums[front];

            size--;

            if (size != 0) {
                front++;

                if (front == BUFF_SIZE) {
                    front = 0;
                }
            }

            return popped;
        }

        public int size() {
            return size;
        }

        public int empty() {
            return size == 0 ? 1 : 0;
        }

        public int front() {
            if (size == 0) {
                return -1;
            }

            return nums[front];
        }

        public int back() {
            if (size == 0) {
                return -1;
            }

            return nums[back];
        }
    }
}
