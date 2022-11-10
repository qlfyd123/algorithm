package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
    static String[] string;

    private static void D(int count) {
        if (string.length < count) throw new RuntimeException();
        else {
            String[] temp = new String[string.length - count];
            if (string.length - count >= 0) System.arraycopy(string, count, temp, 0, string.length - count);
            string = temp.clone();
        }
    }

    private static void reverse() {
        for (int i = 0; i < string.length / 2; i++) {
            String temp = string[i];
            string[i] = string[string.length - 1 - i];
            string[string.length - 1 - i] = temp;
        }
    }

    private static void R(int count) {
        if (count % 2 == 1) {
            reverse();
        }
    }

    public static void nextProcess(int[] curProcess) {
        char c = (char) curProcess[0];
        int count = curProcess[1];
        if (c == 'R') R(count);
        else D(count);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            List<int[]> process = new ArrayList<>();
            int count = 1;
            char curProcess = 'A';
            for (char c : br.readLine().toCharArray()) {
                if (curProcess == 'A') {
                    curProcess = c;
                    continue;
                }
                if (curProcess == c) {
                    count++;
                } else {
                    int[] arr = new int[2];
                    arr[0] = curProcess;
                    arr[1] = count;
                    process.add(arr);
                    curProcess = c;
                    count = 1;
                }
            }
            int[] arr = new int[2];
            arr[0] = curProcess;
            arr[1] = count;
            process.add(arr);

            int n = Integer.parseInt(br.readLine());
            string = new String[n];
            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
            for (int i = 0; i < n; i++) {
                string[i] = st.nextToken();
            }
            try {
                for (int[] cur : process) {
                    nextProcess(cur);
                }
            } catch (RuntimeException e) {
                System.out.println("error");
                continue;
            }
            StringJoiner sj = new StringJoiner(",", "[", "]");
            for (String c : string) {
                sj.add(c);
            }

            System.out.println(sj);
        }
    }
}
