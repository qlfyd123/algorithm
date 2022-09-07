package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
* https://www.acmicpc.net/problem/1548
* */
public class baekjoon_1548 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        if (N < 3) {
            System.out.println(N);
            return;
        }
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);
        int ans = 0;
        for (int i = 0; i < N - 2; i++) {
            for (int j = N - 1; j > i; j--) {
                if (numbers[i] + numbers[i + 1] > numbers[j]) {
                    ans = Math.max(ans, j - i + 1);
                    break;
                }
            }
        }
        System.out.println(ans);
    }
}
