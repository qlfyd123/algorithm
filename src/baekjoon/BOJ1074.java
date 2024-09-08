package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1074 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, r, c;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        br.close();

        System.out.println(solution(N, r, c) - 1);
    }

    private static int solution(int N, int r, int c) {
        int quadrant = calcQuadrant(N, r, c);
        if (N == 1) {
            return quadrant;
        }
        int result = 0;
        int pow = (int) Math.pow(1 << (N - 1), 2);
        switch (quadrant) {
            case 2:
                result += pow;
                c -= 1 << (N - 1);
                break;
            case 3:
                result += pow * 2;
                r -= 1 << (N - 1);
                break;
            case 4:
                result += pow * 3;
                r -= 1 << (N - 1);
                c -= 1 << (N - 1);
                break;
        }
        return result + solution(N - 1, r, c);
    }

    private static int calcQuadrant(int n, int r, int c) {
        int flag = (int) Math.pow(2, n - 1);
        if (r < flag) {
            if (c < flag) {
                return 1;
            } else {
                return 2;
            }
        } else {
            if (c < flag) {
                return 3;
            } else {
                return 4;
            }
        }
    }
}
