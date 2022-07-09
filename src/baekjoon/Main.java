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

        public void ADDH() {
            this.time += 60;
            this.ADDHCount++;
        }

        public void ADDT() {
            this.time += 10;
            this.ADDTCount++;
        }

        public void MINT() {
            this.time -= 10;
            this.MINTCount++;
        }

        public void ADDO() {
            this.time += 1;
            this.ADDOCount++;
        }

        public void MINO() {
            this.time -= 1;
            this.MINOCount++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            Oven oven = Oven.ovenOf(Integer.parseInt(br.readLine()));
            while (oven.time != 0) {
                if (oven.time >= ) {
                    oven.ADDH();
                }
            }
        }
    }

}
