package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_16208 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        long sum = 0;
        long squareSum = 0;
        for (int i = 0; i < n; i++) {
            int temp = Integer.parseInt(st.nextToken());
            sum += temp;
            long curSquareSum = sum * temp;
            squareSum += curSquareSum;
        }

        long result = 0;
        result = (int) Math.pow(sum, 2) - squareSum;
        System.out.println(result);
    }
}
