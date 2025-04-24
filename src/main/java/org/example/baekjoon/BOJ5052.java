package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ5052 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            int N = Integer.parseInt(br.readLine());
            String[] input = new String[N];
            for (int i = 0; i < N; i++) {
                input[i] = br.readLine();
            }
            solution(N, input);
        }
    }

    private static void solution(int N, String[] input) {
        Number[] numbers = new Number[10];
        for (int i = 0; i < 10; i++) {
            numbers[i] = new Number();
        }
        for (int i = 0; i < N; i++) {
            String[] num = input[i].split("");
            Number number = null;
            for (String s : num) {
                int temp = Integer.parseInt(s);
                if (number == null) {
                    number = numbers[temp];
                } else {
                    if (number.isEnd) {
                        System.out.println("NO");
                        return;
                    } else {
                        number = number.children.compute(temp, (k, v) -> (v == null) ? new Number() : v);
                    }
                }
            }
            if (!number.children.isEmpty()) {
                System.out.println("NO");
                return;
            }
            number.isEnd = true;
        }
        System.out.println("YES");
    }

    private static class Number {

        Map<Integer, Number> children;
        boolean isEnd;

        public Number() {
            this.children = new HashMap<>();
            isEnd = false;
        }
    }
}
