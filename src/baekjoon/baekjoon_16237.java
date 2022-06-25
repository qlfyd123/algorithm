package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_16237 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int size[] = new int[5];
        for (int i = 0; i < 5; i++) {

            size[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;

        result += size[4];

        int temp = Math.min(size[2], size[1]);
        result += temp;
        size[2] -= temp;
        size[1] -= temp;

        temp = Math.min(size[0], size[3]);
        result += temp;
        size[0] -= temp;
        size[3] -= temp;

        int one = size[0];
        float two = size[1];
        int three = size[2];
        int four = size[3];

        if (one == 0 && four != 0) {

            result += four;
            if (two == 0) {

                result += three;
            } else {

                result += Math.ceil(two / 2);
            }
        } else if (four == 0 && one != 0) {

            if (two == 0) {

                while (three > 0) {
                    three--;
                    one -= 2;
                    result++;
                }

                while (one > 0) {
                    one -= 5;
                    result++;
                }

            } else {
                while (two > 1) {
                    two -= 2;
                    one--;
                    result++;
                }

                if(two==1){
                    two-=1;
                    one-=3;
                    result++;
                }

                while (one > 0) {
                    one -= 5;
                    result++;
                }

            }
        } else {
            if (three == 0) {
                if (two % 2 == 0) {
                    result += two / 2;
                } else {
                    result += two / 2 + 1;
                }
            } else {
                result += three;
            }
        }

        System.out.print(result);
    }

}