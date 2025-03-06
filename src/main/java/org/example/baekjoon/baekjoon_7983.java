package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon_7983 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int assignmentNum = Integer.parseInt(br.readLine());

        int[][] assignment = new int[assignmentNum][2];

        StringTokenizer st;
        for (int i = 0; i < assignmentNum; i++) {
            st = new StringTokenizer(br.readLine());
            int duringTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            assignment[i][0] = duringTime;
            assignment[i][1] = endTime;
        }

        Arrays.sort(assignment, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            } else {
                return o2[1] - o1[1];
            }
        });

        int time = assignment[0][1];

        for (int[] timeTable : assignment) {
            if (time > timeTable[1]) {
                time = timeTable[1];
            }

            time -= timeTable[0];
        }

        System.out.println(time);
    }
}
