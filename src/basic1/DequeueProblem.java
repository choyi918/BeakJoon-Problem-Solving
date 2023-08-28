package basic1;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/10866
 * 풀이1 : -> 시간초과
 * 사용한 자료구조 : LinkedList, 자바 LinkedList가 지원하는 메서드로 간단히 구현
 * 풀이2 : -> 시간초과, 이유 : 출력과정에서 String.format()을 이용. 제거하였더니 해결됨
 * 사용한 자료구조 : RingBuffer로 front, back 포인터로 구현할 operation 구현
 */

public class DequeueProblem {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        int lineCount = Integer.parseInt(br.readLine());

        DequeueNative dequeue = new DequeueNative();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < lineCount; i++) {
            String commandLine = br.readLine();

            if (commandLine.equals("pop_front")) {
                sb.append(dequeue.popFront());
                sb.append(System.lineSeparator());
            } else if (commandLine.equals("pop_back")) {
                sb.append(dequeue.popBack());
                sb.append(System.lineSeparator());
            } else if (commandLine.equals("front")) {
                sb.append(dequeue.front());
                sb.append(System.lineSeparator());
            } else if (commandLine.equals("back")) {
                sb.append(dequeue.back());
                sb.append(System.lineSeparator());
            } else if (commandLine.equals("size")) {
                sb.append(dequeue.size());
                sb.append(System.lineSeparator());
            } else if (commandLine.equals("empty")) {
                sb.append(dequeue.empty());
                sb.append(System.lineSeparator());
            } else {
                String[] tokens = commandLine.split(" ");

                String command = tokens[0];

                if (command.equals("push_front")) {
                    dequeue.pushFront(Integer.parseInt(tokens[1]));
                } else if (command.equals("push_back")) {
                    dequeue.pushBack(Integer.parseInt(tokens[1]));
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();

        isr.close();
        br.close();
        osw.close();
        bw.close();
    }

    public static class DequeueNative {
        public static int BUFF_SIZE = 10000;
        public int[] nums = new int[BUFF_SIZE];
        public int front = 0;
        public int back = 0;
        public int size = 0;

        public void pushFront(int value) {
            if (size != 0) {
                front--;

                if (front == -1) {
                    front = BUFF_SIZE - 1;
                }
            }

            nums[front] = value;
            size++;
        }

        public void pushBack(int value) {
            if (size != 0) {
                back++;

                if (back == BUFF_SIZE) {
                    back = 0;
                }

            }

            nums[back] = value;
            size++;
        }

        public int popFront() {
            if (size == 0) {
                return -1;
            }

            int popped = nums[front];

            if (size > 1) {
                if (front == BUFF_SIZE - 1) {
                    front = 0;
                } else {
                    front++;
                }
            }

            size--;

            return popped;
        }

        public int popBack() {
            if (size == 0) {
                return -1;
            }

            int pooped = nums[back];

            if (size > 1) {
                if (back == 0) {
                    back = BUFF_SIZE - 1;
                } else {
                    back--;
                }
            }

            size--;

            return pooped;
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

        public int empty() {
            return size == 0 ? 1 : 0;
        }

        public int size() {
            return size;
        }
    }

    public static class Dequeue {

        public LinkedList<Integer> dq = new LinkedList();

        public void pushFront(final int n) {
            dq.addFirst(n);
        }

        public void pushBack(final int n) {
            dq.addLast(n);
        }

        public int popFront() {
            if (empty() == 1) {
                return -1;
            }

            return dq.removeFirst();
        }

        public int popBack() {
            if (empty() == 1) {
                return -1;
            }

            return dq.removeLast();
        }

        public int size() {
            return dq.size();
        }

        public int empty() {
            return dq.isEmpty() ? 1 : 0;
        }

        public int front() {
            if (empty() == 1) {
                return -1;
            }

            return dq.getFirst();
        }

        public int back() {
            if (empty() == 1) {
                return -1;
            }

            return dq.getLast();
        }
    }
}
