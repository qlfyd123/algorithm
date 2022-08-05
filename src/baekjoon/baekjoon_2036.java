package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
/*
*
* https://www.acmicpc.net/problem/2036
*
* */
public class baekjoon_2036 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Queue<Integer> plus = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> minus = new PriorityQueue<>();
        while (n-- > 0) {
            int temp = Integer.parseInt(br.readLine());
            if (temp > 0) {
                plus.add(temp);
            } else {
                minus.add(temp);
            }
        }

        long ans = 0;
        int temp1;
        int temp2;
        while (!plus.isEmpty()) {
            temp1 = plus.poll();
            if (!plus.isEmpty()) {
                temp2 = plus.poll();
                if (temp1 <= 1 || temp2 <= 1) {
                    ans += temp1 + temp2;
                } else {
                    ans += (long) temp1 * temp2;
                }
            } else {
                ans += temp1;
            }
        }

        while (!minus.isEmpty()) {
            temp1 = minus.poll();
            if (!minus.isEmpty()) {
                temp2 = minus.poll();
                ans += (long) temp1 * temp2;
            } else {
                ans += temp1;
            }
        }

        System.out.println(ans);
    }
}
