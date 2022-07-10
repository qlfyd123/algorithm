package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static class Oven {
        int time;
        int ADDHCount = 0;
        int ADDTCount = 0;
        int MINTCount = 0;
        int ADDOCount = 0;
        int MINOCount = 0;

        private Oven(int time) {
            this.time = time;
        }

        public static Oven ovenOf(int time) {
            return new Oven(time);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            Oven oven = Oven.ovenOf(Integer.parseInt(br.readLine()));
            int remain = oven.time % 60;
            int remain2 = oven.time % 10;

            oven.ADDHCount += oven.time / 60;

            if (remain >= 40) {
                oven.ADDHCount++;
                oven.MINTCount += 6 - remain / 10;
            } else {
                oven.ADDTCount += remain / 10;
            }

            if (remain2 > 5) {
                oven.ADDTCount++;
                oven.MINOCount += 10 - remain2;
            } else {
                oven.ADDOCount += remain2;
            }

            while (oven.ADDTCount > 0 && oven.MINTCount > 0) {
                oven.ADDTCount--;
                oven.MINTCount--;
            }

            String sb = oven.ADDHCount +
                    " " +
                    oven.ADDTCount +
                    " " +
                    oven.MINTCount +
                    " " +
                    oven.ADDOCount +
                    " " +
                    oven.MINOCount +
                    " ";

            System.out.println(sb);
        }
    }

}
