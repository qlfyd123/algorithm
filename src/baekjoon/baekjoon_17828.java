package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *
 * https://www.acmicpc.net/problem/17828
 *
 * */
public class baekjoon_17828 {
    static final int ascii = 64;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int length = Integer.parseInt(st.nextToken());
        if (N > length || N * 26 < length) {
            System.out.println("!");
            return;
        }
        StringBuffer sb = new StringBuffer();
        int count = 0;
        int flag = 26;
        while (length > flag) {
            if (length - flag > N - count) {
                length -= flag;
                sb.append((char) (flag + ascii));
                count++;
            } else {
                break;
            }
        }
        sb.append((char) (length - (N - count - 1) + ascii));
        sb.append(String.valueOf((char) (1 + ascii)).repeat(Math.max(0, N - count - 1)));

        System.out.println(sb.reverse());
    }
}
