package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon_20044 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()) * 2;
        int[] performance = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            performance[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(performance);

        int result = maxPerformance(performance);

        System.out.println(result);

    }

    private static int maxPerformance(int[] performance) {
        int min = 0;
        for (int i = 0; i < performance.length; i++) {
            int temp = performance[i] + performance[performance.length - 1 - i];
            if (i == 0) min = temp;
            else if (temp < min) min = temp;
        }
        return min;
    }
}
