package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @see <a href="https://www.acmicpc.net/problem/2042">백준2042</a>
 */
public class baekjoon_2042 {
    static long[] segmentTree;
    static long[] numbers;

    private static long setTree(int start, int end, int nodeIndex) {
        if (start == end) {
            segmentTree[nodeIndex] = numbers[start];
        } else {
            int midValue = (start + end) / 2;
            segmentTree[nodeIndex] = setTree(start, midValue, nodeIndex * 2) + setTree(midValue + 1, end, nodeIndex * 2 + 1);
        }
        return segmentTree[nodeIndex];
    }

    private static void update(int index, long value, int nodeIndex, int start, int end) {
        long previousValue = numbers[index];
        segmentTree[nodeIndex] += (value - previousValue);
        if (start != end) {
            int midValue = (start + end) / 2;
            if (index <= midValue) {
                update(index, value, nodeIndex * 2, start, midValue);
            } else {
                update(index, value, nodeIndex * 2 + 1, midValue + 1, end);
            }
        } else {
            numbers[index] = value;
        }
    }

    /**
     * @param start     현재 노드의 구간 시작 인덱스
     * @param end       현재 노드의 구간 종료 인덱스
     * @param nodeIndex 현재 노드
     * @param left      구하고자 하는 구간합의 시작 인덱스
     * @param right     구하고자 하는 구간합의 종료 인덱스
     * @return 구간값
     */
    private static long sum(int start, int end, int nodeIndex, int left, int right) {
        if (start == left & end == right) {
            return segmentTree[nodeIndex];
        }
        int midValue = (start + end) / 2;
        if (midValue < left) return sum(midValue + 1, end, nodeIndex * 2 + 1, left, right);
        else if (midValue >= right) return sum(start, midValue, nodeIndex * 2, left, right);
        else
            return sum(start, midValue, nodeIndex * 2, left, midValue) + sum(midValue + 1, end, nodeIndex * 2 + 1, midValue + 1, right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        numbers = new long[Integer.parseInt(st.nextToken()) + 1];
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        for (int i = 1; i < numbers.length; i++) {
            numbers[i] = Long.parseLong(br.readLine());
        }

        segmentTree = new long[numbers.length * 4];
        setTree(1, numbers.length - 1, 1);
        while (M > 0 | K > 0) {
            StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
            boolean exchange = st2.nextToken().equals("1");
            if (exchange) {
                int index = Integer.parseInt(st2.nextToken());
                long value = Long.parseLong(st2.nextToken());
                update(index, value, 1, 1, numbers.length - 1);
                M--;
            } else {
                int start = Integer.parseInt(st2.nextToken());
                int end = Integer.parseInt(st2.nextToken());
                long sum = sum(1, numbers.length - 1, 1, start, end);
                System.out.println(sum);
                K--;
            }
        }
    }
}