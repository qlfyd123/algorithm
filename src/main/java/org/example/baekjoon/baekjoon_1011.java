package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @see <a href="https://www.acmicpc.net/problem/1011">백준 1011</a>
 */
public class baekjoon_1011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int distance = (Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken())) * -1;
            int sum = 1;
            int ans = 1;
            while (distance - sum >= 2 * ans + 1) {
                sum += 2 * ans + 1;
                ans++;
            }
            int count = ans * 2 - 1;
            distance -= sum;
            while (distance > 0) {
                if (distance >= ans) {
                    distance -= ans;
                    count++;
                } else ans--;

            }
            System.out.println(count);
        }
    }
}
