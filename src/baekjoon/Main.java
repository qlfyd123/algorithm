package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] location = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] requireStickRange = IntStream.range(0, 3)
                .map(value -> location[5 - value] - location[value])
                .sorted()
                .toArray();
        boolean[] success = {false, false, false};
        int N = Integer.parseInt(br.readLine());
        int[] stick = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        int stickRangeSum = 0;
        int index = 2;
        for (int i = stick.length - 1; i >= 0 & index >= 0; i--) {
            if (stickRangeSum == 0) {
                stickRangeSum = stick[i];
            } else {
                if (stickRangeSum == requireStickRange[index]) {
                    stickRangeSum = 0;
                    success[index] = true;
                    index--;
                } else if (stickRangeSum > requireStickRange[index]) {
                    stickRangeSum -= stick[i];
                } else {
                    stickRangeSum += stick[i];
                }
            }
        }
        if (!success[0]) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }


    }
}
