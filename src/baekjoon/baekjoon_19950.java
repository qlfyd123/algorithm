package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

/*
 * https://www.acmicpc.net/problem/19950
 * */
public class baekjoon_19950 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] location = br.readLine().split(" ");
        double distanceBetween;
        distanceBetween = Math.sqrt(IntStream.range(0, 3)
                .map(value -> (int) Math.pow(Integer.parseInt(location[value + 3]) - Integer.parseInt(location[value]), 2))
                .sum());
        int N = Integer.parseInt(br.readLine());

        int[] sticks = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        double stickLengthSum = 0;
        for (int i = 0; i < N - 1; i++)
            stickLengthSum += sticks[i];

        int maxStickLength = sticks[N - 1];
        if (stickLengthSum + maxStickLength < distanceBetween)
            System.out.println("NO");
        else if (stickLengthSum + maxStickLength == distanceBetween)
            System.out.println("YES");
        else {
            if (maxStickLength - stickLengthSum <= distanceBetween)
                System.out.println("YES");
            else System.out.println("NO");
        }
    }
}