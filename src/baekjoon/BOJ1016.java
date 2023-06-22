package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 *  <a href="https://www.acmicpc.net/problem/1016">BOJ 1016 제곱 ㄴㄴ 수</a>
 * */
public class BOJ1016 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());
        boolean[] notPrime = new boolean[(int) (max - min + 1)];
        for (int i = 2; i <= Math.sqrt(max); i++) {
            long pow = (long) Math.pow(i, 2);
            long powNumber = min / pow * pow;
            if (powNumber < min) powNumber += pow;
            while (powNumber <= max) {
                notPrime[(int) (powNumber - min)] = true;
                powNumber += pow;
            }
        }
        int count = 0;
        for (boolean b : notPrime) {
            if (!b) {
                count++;
            }
        }
        System.out.println(count);
    }
}
