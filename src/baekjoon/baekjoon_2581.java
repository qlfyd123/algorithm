package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_2581 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int M = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        int[] number = new int[N + 1];
        for (int i = 0; i < number.length; i++) {

            number[i] = i;
        }
        number[1] = 0;
        number[0] = 0;

        int flag = 2;

        while (flag <= (int) Math.sqrt(N)) {

            int i = 2;
            while (flag * i <= N) {

                number[flag * i] = 0;
                i++;
            }
            flag++;
        }

        int sum = 0;
        boolean isMinPrimeNumber = false;
        int minPrimeNumber = 0;

        for (int primeNumber : number) {

            if (primeNumber != 0 && primeNumber >= M) {

                sum += primeNumber;

                if (isMinPrimeNumber == false) {

                    minPrimeNumber = primeNumber;
                    isMinPrimeNumber = true;
                }
            }
        }

        if (sum != 0 && minPrimeNumber != 0) {

            System.out.println(sum);
            System.out.println(minPrimeNumber);
        } else {

            System.out.println(-1);
        }

    }
}
