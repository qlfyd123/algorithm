package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> acidList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        br.close();
        for (int i = 0; i < N; i++) {
            int acidValue = Integer.parseInt(st.nextToken());
            acidList.add(acidValue);
        }
        acidList.sort(Comparator.naturalOrder());
        int minAcidValue = Integer.MAX_VALUE;
        int leftIndex = 0;
        int rightIndex = acidList.size() - 1;
        int minAcidValueLeftIndex = 0;
        int minAcidValueRightIndex = 0;
        while (leftIndex < rightIndex) {
            int mixedAcid = acidList.get(leftIndex) + acidList.get(rightIndex);
            if (Math.abs(mixedAcid) < Math.abs(minAcidValue)) {
                minAcidValue = mixedAcid;
                minAcidValueLeftIndex = leftIndex;
                minAcidValueRightIndex = rightIndex;
            }
            if (mixedAcid < 0) {
                leftIndex++;
            } else {
                rightIndex--;
            }
        }
        System.out.println(acidList.get(minAcidValueLeftIndex) + " " + acidList.get(minAcidValueRightIndex));
    }
}