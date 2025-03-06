package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class BOJ1253 {

    static int N;
    static int[] arr;
    static Set<Integer> visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);
        visited = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            int element = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                int nextElement = arr[j];
                int visitedIdx = element + nextElement;
                if (element != 0 && nextElement != 0) {
                    visited.add(visitedIdx);
                } else {
                    if (j + 1 < arr.length) {
                        if (arr[j + 1] == visitedIdx) {
                            visited.add(visitedIdx);
                        }
                    }
                    if (i - 1 >= 0) {
                        if (arr[i - 1] == visitedIdx) {
                            visited.add(visitedIdx);
                        }
                    }
                }
            }
        }
        AtomicInteger count = new AtomicInteger();
        Arrays.stream(arr).forEach(i -> {
            if (visited.contains(i)) {
                count.getAndIncrement();
            }
        });
        System.out.println(count.get());
    }
}
