package basic1;

import java.io.*;
import java.util.LinkedList;

/**
 * https://www.acmicpc.net/problem/1406
 * 풀이: -> LinkedList를 이용한 문제풀이(시간초과)
 * 1. cursor라는 변수를 만들고 초기값은 초기 주어진 문자열의 길이로 함.
 * 2. 문자열은 중간에 문자를 추가할 수 있는 LinkedList(characters)로 함
 * 3. P 명령어가 나온다면 해당 커서가 가지고 있는 값을 인덱스로하는 곳에 문자를 넣음. 커서의 값은 +1. -> O(N)
 * 4. L 명령어가 나온다면 커서의 값을 -1함. 만약 기존 커서 값이 0이라면 -1하지 않음 -> O(1)
 * 5. D 명령어가 나온다면 커서의 값을 +1함. 만약 기존 커서 값이 characters 길이와 같다면 +1하지 않음 -> O(1)
 * 6. B 명령어가 나온다면 cursor - 1을 인덱스로하는 문자를 지우고 cursor의 값도 -1함. 만약 기존 커서 값이 0이라면 이 작동을 하지 않음 -> O(N)
 * 7. lineCount만큼 3~6 중 하나를 계속 반복함
 * <p>
 * 풀이 : -> 스택(LinkedList를 스택으로 사용)을 이용한 문제풀이
 * 1. 스택 두개를 준비한다. (메인스택, 서브스택)
 * 2. 메인스택의 TOP은 커서가 위치하기로한다.
 * 3. 명령어가 L이면 커서를 왼쪽으로 옮긴다. -> 메인스택에서 POP한 문자를 서브스택으로 PUSH한다. -> O(1)
 * 4. 명령어가 D이면 커서를 오른쪽으로 옮긴다. -> 서브스택에서 POP한 문자를 메인스택으로 PUSH한다. -> O(1)
 * 5. 명령어가 B이면 메인스택에서 POP한 문자를 그냥 없앤다. -> O(1)
 * 6. 명령어가 P이면 메인스택에 PUSH 한다. -> O(1)
 */
public class Editor {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        String initialString = br.readLine();

        int lineCount = Integer.parseInt(br.readLine());

        LinkedList<Character> mainStack = new LinkedList();
        LinkedList<Character> subStack = new LinkedList();

        char[] charArray = initialString.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            mainStack.push(charArray[i]);
        }

        for (int i = 0; i < lineCount; i++) {
            String commandLine = br.readLine();

            char command = commandLine.charAt(0);
            if (command == 'P') {
                char value = commandLine.charAt(2);
                mainStack.push(value);
            } else if (command == 'B' && !mainStack.isEmpty()) {
                mainStack.pop();
            } else if (command == 'L' && !mainStack.isEmpty()) {
                subStack.push(mainStack.pop());
            } else if (command == 'D' && !subStack.isEmpty()) {
                mainStack.push(subStack.pop());
            }
        }

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        /*
            출력
         */
        while (!mainStack.isEmpty())
            bw.write(mainStack.removeLast());

        while (!subStack.isEmpty())
            bw.write(subStack.removeFirst());

        bw.flush();

        isr.close();
        osw.close();
        br.close();
        bw.close();
    }
}
