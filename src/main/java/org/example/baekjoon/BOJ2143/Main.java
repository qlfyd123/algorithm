package org.example.baekjoon.BOJ2143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        List<Integer> A = new ArrayList<>();
        while (st.hasMoreTokens()) {
            if (A.isEmpty()) {
                A.add(Integer.parseInt(st.nextToken()));
            } else {
                A.add(A.get(A.size() - 1) + Integer.parseInt(st.nextToken()));
            }
        }
        int m = Integer.parseInt(br.readLine());
        List<Integer> B = new ArrayList<>();
        st = new StringTokenizer(br.readLine(), " ");
        while (st.hasMoreTokens()) {
            if (B.isEmpty()) {
                B.add(Integer.parseInt(st.nextToken()));
            } else {
                B.add(B.get(B.size() - 1) + Integer.parseInt(st.nextToken()));
            }
        }

        /* 각 배열의 부분 배열의 합을 전부 저장 */
        SubArray ASubArray = getSubArray(n, A);
        SubArray BSubArray = getSubArray(m, B);

        /* A의 서브 배열을 기준으로 B 배열에서 이분 탐색으로 두 부분 배열의 합이 T인 배열을 탐색 */
        ASubArray.subArraySum.sort(Comparator.comparingInt(a -> a));
        BSubArray.subArraySum.sort(Comparator.comparingInt(a -> a));

        long count = 0;
        for (int i = 0; i < ASubArray.subArraySum.size(); i++) {
            int a = ASubArray.subArraySum.get(i);
            int target = T - a;
            int bCount = BSubArray.subArrayNumberCount.getOrDefault(target, -1);

            if (bCount != -1) {
                count += (long) bCount * ASubArray.subArrayNumberCount.get(a);
            }
        }
        System.out.println(count);
    }

    private static SubArray getSubArray(int n, List<Integer> A) {
        Map<Integer, Integer> SubArrayNumberCount = new HashMap<>();
        List<Integer> SubArray = new ArrayList<>();

        for (int i = -1; i < n - 1; i++) {
            int left = i == -1 ? 0 : A.get(i);
            for (int j = i + 1; j < n; j++) {
                int right = A.get(j);
                int sum = right - left;

                if (SubArrayNumberCount.containsKey(sum)) {
                    SubArrayNumberCount.put(sum, SubArrayNumberCount.get(sum) + 1);
                } else {
                    SubArrayNumberCount.put(sum, 1);
                    SubArray.add(sum);
                }
            }
        }

        return new SubArray(SubArray, SubArrayNumberCount);
    }

    private static class SubArray {

        List<Integer> subArraySum;
        Map<Integer, Integer> subArrayNumberCount;

        public SubArray(List<Integer> subArraySum, Map<Integer, Integer> subArrayNumberCount) {
            this.subArraySum = subArraySum;
            this.subArrayNumberCount = subArrayNumberCount;
        }
    }
}
