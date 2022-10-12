package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringJoiner;

public class baekjoon_2759 {
    static String cake;

    public static void flip(int index) {
        StringBuilder left = new StringBuilder(cake.substring(0, index + 1));

        cake = left.reverse() + cake.substring(index + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            StringBuilder sb = new StringBuilder(br.readLine().replace(" ", ""));
            int K = Integer.parseInt(String.valueOf(sb.charAt(0)));
            sb.deleteCharAt(0);
            cake = sb.toString();
            char[] sortedPanCake = cake.toCharArray();
            Arrays.sort(sortedPanCake);


            StringJoiner sj = new StringJoiner(" ");
            int count = 0;
            for (int i = K - 1; i >= 1; i--) {
                int index = cake.indexOf(sortedPanCake[i]);
                if (index == 0) {
                    sj.add(String.valueOf(i + 1));
                    flip(i);
                    count++;
                } else if (index < i) {
                    sj.add(String.valueOf(index + 1));
                    flip(index);
                    sj.add(String.valueOf(i + 1));
                    flip(i);
                    count += 2;
                }
            }

            System.out.print(count + " ");
            System.out.println(sj);
        }
    }
}
