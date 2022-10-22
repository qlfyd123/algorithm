package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] location = br.readLine().split(" ");
        double distanceBetween;
        distanceBetween = Math.sqrt(IntStream.range(0, 3)
                .map(value -> (int) Math.pow(Integer.parseInt(location[5 - value]) - Integer.parseInt(location[value]), 2))
                .sum());
        int N = Integer.parseInt(br.readLine());

        int[] sticks = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        double stickLengthSum = 0;
        for (int stick : sticks)
            stickLengthSum += stick;

        if (stickLengthSum < distanceBetween)
            System.out.println("NO");
        else if (stickLengthSum - sticks[N - 1] + distanceBetween < sticks[N - 1])
            System.out.println("NO");
        else System.out.println("YES");
    }
}