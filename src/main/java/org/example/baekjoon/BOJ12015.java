package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ12015 {

    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Integer> list = new ArrayList<>();
        list.add(arr[0]);
        for (int i = 1; i < N; i++) {
            if (list.get(list.size() - 1) < arr[i]) {
                list.add(arr[i]);
            } else {
                int index = binarySearch(list, arr[i]);
                list.set(index, arr[i]);
            }
        }
        System.out.println(list.size());
    }

    private static int binarySearch(List<Integer> list, int target) {
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
