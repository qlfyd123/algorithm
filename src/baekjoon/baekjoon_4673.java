package baekjoon;

import java.util.Arrays;

public class baekjoon_4673 {
    public static void main(String[] args) {
        boolean[] selfNumber = new boolean[10001];
        Arrays.fill(selfNumber, true);
        selfNumber[0] = false;
        calSelfNumber(10);
        for (int i = 1; i < 10001; i++) {
            int index = calSelfNumber(i);
            if (index < 10001) {
                selfNumber[index] = false;
            }
        }

        int index = 1;

        for (boolean i : selfNumber) {
            if (i)
                System.out.println(index - 1);
            index++;
        }

    }

    public static int calSelfNumber(int number) {
        int sum = number;
        while (number > 0) {
            sum += number % 10;
            number = number / 10;
        }
        return sum;
    }
}
