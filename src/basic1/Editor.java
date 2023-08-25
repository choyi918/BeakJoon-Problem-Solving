package basic1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1406
 * 풀이:
 * 1. cursor라는 변수를 만들고 초기값은 초기 주어진 문자열의 길이로 함.
 * 2. 문자열은 중간에 문자를 추가할 수 있는 LinkedList(characters)로 함
 * 3. P 명령어가 나온다면 해당 커서가 가지고 있는 값을 인덱스로하는 곳에 문자를 넣음. 커서의 값은 +1.
 * 4. L 명령어가 나온다면 커서의 값을 -1함. 만약 기존 커서 값이 0이라면 -1하지 않음
 * 5. D 명령어가 나온다면 커서의 값을 +1함. 만약 기존 커서 값이 characters 길이와 같다면 +1하지 않음
 * 6. B 명령어가 나온다면 cursor - 1을 인덱스로하는 문자를 지우고 cursor의 값도 -1함. 만약 기존 커서 값이 0이라면 이 작동을 하지 않음
 * 7. lineCount만큼 3~6 중 하나를 계속 반복함
 */
public class Editor {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        LinkedList<Character> characters = new LinkedList<>();

        String initialString = br.readLine();

        for (int i = 0; i < initialString.length(); i++) {
            characters.add(initialString.charAt(i));
        }

        int cursor = characters.size();

        int lineCount = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lineCount; i++) {
            String commandLine = br.readLine();
            StringTokenizer st = new StringTokenizer(commandLine, " ");

            String command = st.nextToken();
            if (command.equals("P")) {
                String ch = st.nextToken();
                characters.add(cursor++, ch.charAt(0));
            } else if (command.equals("L") && cursor != 0) {
                cursor--;
            } else if (command.equals("D") && cursor < characters.size()) {
                cursor++;
            } else if (command.equals("B") && cursor != 0) {
                characters.remove(--cursor);
            }
        }

        characters.stream().forEach(character -> sb.append(character));

        System.out.println(sb);

        br.close();
        isr.close();
    }
}
