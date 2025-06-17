package org.example.baekjoon.BOJ1339;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    private static final char A = 'A';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] weights = new int[26];
        int[] numbers = new int[26];
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            words[i] = word;
            char[] charArray = word.toCharArray();
            // 알파벳의 자릿수에 따라 가중치 계산
            for (int j = charArray.length - 1; j >= 0; j--) {
                char c = charArray[j];
                weights[c - A] += (int) Math.pow(10, charArray.length - 1 - j);
            }
        }
        // 가중치가 높은 알파벳부터 숫자를 할당하기 위해 우선순위 큐 사용
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] > 0) {
                queue.add(new int[]{i, weights[i]});
            }
        }
        // 가중치가 높은 알파벳부터 9부터 0까지 숫자를 할당
        int curNumber = 9;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int index = cur[0];
            numbers[index] = curNumber--;
        }

        int totalSum = 0;
        for (String word : words) {
            char[] charArray = word.toCharArray();
            int sum = 0;
            for (char c : charArray) {
                sum = sum * 10 + numbers[c - A];
            }
            totalSum += sum;
        }

        System.out.println(totalSum);
    }
}
