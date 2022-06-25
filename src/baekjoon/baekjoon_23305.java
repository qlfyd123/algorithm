package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon_23305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] lecture = new int[N];
        int[] change = new int[1000001];
        Arrays.fill(change, 0);
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int max = 0;
        for (int i = 0; i < N; i++) {
            lecture[i] = Integer.parseInt(st.nextToken());
            int index = Integer.parseInt(st2.nextToken());
            if (max < index) {
                max = index;
            }
            change[index]++;
        }
        for (int i : lecture) {
            change[i]--;
        }

        int result = 0;
        for (int i = 1; i < max + 1; i++) {
            if (change[i] > 0) {
                result += change[i];
            }
        }

        System.out.println(result);
    }
}