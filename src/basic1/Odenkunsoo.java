package basic1;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Odenkunsoo {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        br.readLine();

        String[] s = br.readLine().split(" ");

        String result = solve(s);

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        bw.write(result);
        bw.flush();

        isr.close();
        osw.close();
        br.close();
        bw.close();
    }

    private static String solve(String[] numsString) {
        int[] nums = new int[numsString.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = 0; i < numsString.length; i++) {
            nums[i] = Integer.parseInt(numsString[i]);

            if (map.containsKey(nums[i]))
                map.put(nums[i], map.get(nums[i]) + 1);
            else
                map.put(nums[i], 1);
        }

        int[] result = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int n = nums[i];

            while (true) {

                if (stack.isEmpty()) {
                    result[i] = -1;
                    stack.push(n);
                    break;
                }

                Integer popped = stack.pop();

                Integer poppedCount = map.get(popped);
                Integer nCount = map.get(n);

                if (poppedCount > nCount) {
                    result[i] = popped;
                    stack.push(popped);
                    stack.push(n);
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(result[i]);

            if (i != result.length - 1)
                sb.append(" ");
        }

        return sb.toString();
    }
}
