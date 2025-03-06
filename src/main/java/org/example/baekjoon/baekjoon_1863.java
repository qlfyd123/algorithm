package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class baekjoon_1863 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] skyLine = new int[n][2];
        StringTokenizer st;
        int minHeight = -1;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            skyLine[i][0] = Integer.parseInt(st.nextToken());
            skyLine[i][1] = Integer.parseInt(st.nextToken());
            if (minHeight == -1) {
                minHeight = skyLine[i][1];
            } else if (minHeight > skyLine[i][1]) {
                minHeight = skyLine[i][1];
            }
        }

        Arrays.sort(skyLine, Comparator.comparingInt(o -> o[0]));
        int count = 0;
        Stack<Integer> heightStack = new Stack<>();
        for (int[] skyline : skyLine) {
            while (!heightStack.isEmpty() && skyline[1] < heightStack.peek()) {
                heightStack.pop();
                count++;
            }
            if (!heightStack.isEmpty() && skyline[1] == heightStack.peek()) {
            } else heightStack.push(skyline[1]);
        }

        while (!heightStack.isEmpty()) {
            if (heightStack.pop() != 0) {
                count++;
            }
        }
        System.out.println(count);
    }
}
