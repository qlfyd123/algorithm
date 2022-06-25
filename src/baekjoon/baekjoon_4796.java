package baekjoon;

import java.io.IOException;
import java.util.Scanner;

public class baekjoon_4796 {
    public static void main(String[] args) throws IOException {
        int i = 1;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {

            int L = sc.nextInt();
            int P = sc.nextInt();
            int V = sc.nextInt();

            if (L == 0 && P == 0 && V == 0) {
                sc.close();
                break;
            }

            int count = 0;

            if (P < V) {
                count = ((V / P) * L + Math.min((V % P), L));
            } else {
                count = L;
            }

            System.out.println("Case " + i + ": " + count);
            i++;

        }
    }
}