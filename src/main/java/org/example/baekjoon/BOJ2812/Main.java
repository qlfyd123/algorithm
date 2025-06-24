package org.example.baekjoon.BOJ2812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String num = br.readLine();

        int[] digits = getDigitsArray(num, N);
        Map<Integer, Queue<Integer>> map = buildMap(digits);

        StringBuilder sb = new StringBuilder();

        int currentIndex = 1;

        while (K > 0) {
            int nextIndex = getNextNumberIndex(map, currentIndex, K);

            if (nextIndex == -1) {
                break;
            } else {
                sb.append(digits[nextIndex]);
                K -= nextIndex - currentIndex;
                currentIndex = nextIndex + 1;
            }
        }

        for (int i = currentIndex; i < N + 1; i++) {
            sb.append(digits[i]);
        }
        /*
        * 모든 숫자가 최적이서서 K가 0이 되지 않는 경우, 남은 숫자들을 뒤에서부터 K개를 제거합니다.
        * ===================
        * ex)
        * 4 2
        * 9999
        * 결과: 99
        * */
        sb.delete(sb.length() - K, sb.length());

        System.out.println(sb);
    }


    /**
     * @param num 입력받은 숫자
     * @param N   숫자의 길이
     * @return 숫자의 각 자릿수를 분리해 integer 배열에 저장해 반환 합니다. </br>
     * <p>
     * 0번째 인덱스는 사용하지 않습니다. </br>
     * <p>
     * 인덱스는 자릿수의 역순으로 높은 자리의 숫자일수록 인덱스가 작습니다.
     */
    private static int[] getDigitsArray(String num, int N) {
        final char ZERO = '0';
        int[] digits = new int[N + 1];
        int index = 1;
        for (char c : num.toCharArray()) {
            int digit = c - ZERO;
            digits[index++] = digit;
        }
        return digits;
    }

    /**
     * @param digits 숫자의 각 자릿수를 저장한 배열
     * @return 각 숫자(0~9)를 키로 하고, 해당 숫자가 나타나는 인덱스를 저장하는 우선순위 큐를 값으로 하는 맵을 반환합니다. </br>
     * <p>
     * 이 맵은 각 숫자에 대해 해당 숫자가 나타나는 위치를 높은 자리에서 낮은 자리 순서로 저장합니다.
     */
    private static Map<Integer, Queue<Integer>> buildMap(int[] digits) {
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        for (int i = 0; i <= 9; i++) {
            map.put(i, new PriorityQueue<>());
        }

        int N = digits.length - 1;
        for (int i = 1; i <= N; i++) {
            int digit = digits[i];
            Queue<Integer> queue = map.get(digit);
            queue.offer(i);
        }
        return map;
    }

    /**
     * @param map          숫자와 해당 숫자가 나타나는 인덱스를 저장한 맵
     * @param currentIndex 현재 인덱스
     * @param K            K 값, 현재 인덱스에서 K 이하의 거리에 있는 다음 숫자의 인덱스를 찾습니다.
     * @return 다음 숫자의 인덱스를 반환합니다. </br>
     * <p>
     * 이 메서드는 현재 인덱스에서 K 이하의 거리에 있는 숫자를 찾습니다. </br>
     * <p>
     * 숫자는 9부터 0까지 역순으로 탐색하며, 해당 숫자가 나타나는 인덱스가 현재 인덱스보다 크고 K 이하의 거리에 있는 경우 그 인덱스를 반환합니다. </br>
     */
    private static int getNextNumberIndex(Map<Integer, Queue<Integer>> map, int currentIndex, int K) {
        for (int i = 9; i >= 0; i--) {
            Queue<Integer> queue = map.get(i);
            while (!queue.isEmpty() && queue.peek() < currentIndex) {
                queue.poll();
            }
            if (!queue.isEmpty() && queue.peek() - currentIndex <= K) {
                return queue.poll();
            }
        }
        return -1;
    }
}
