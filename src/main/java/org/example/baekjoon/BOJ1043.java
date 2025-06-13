package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/1043">BOJ1043 거짓말</a>
 * */
public class BOJ1043 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int truthCount = Integer.parseInt(st.nextToken());
        boolean[] truthKnowPerson = new boolean[N + 1];
        for (int i = 0; i < truthCount; i++) {
            truthKnowPerson[Integer.parseInt(st.nextToken())] = true;
        }

        Map<Integer, List<Integer>> party = new HashMap<>();
        Map<Integer, List<Integer>> reverseParty = new HashMap<>();

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int participantCount = Integer.parseInt(st.nextToken());
            for (int j = 0; j < participantCount; j++) {
                int person = Integer.parseInt(st.nextToken());
                if (truthKnowPerson[person]) {
                    queue.add(i);
                }
                party.computeIfAbsent(i, k -> new ArrayList<>()).add(person);
                reverseParty.computeIfAbsent(person, k -> new ArrayList<>()).add(i);
            }
        }

        boolean[] visited = new boolean[M];
        while (!queue.isEmpty()) {
            int partyIndex = queue.poll();
            visited[partyIndex] = true;
            List<Integer> participants = party.getOrDefault(partyIndex, new ArrayList<>());

            for (int participant : participants) {
                List<Integer> parties = reverseParty.getOrDefault(participant, new ArrayList<>());
                for (int nextParty : parties) {
                    if (!visited[nextParty]) {
                        queue.add(nextParty);
                    }
                }
            }
        }
        int count = 0;
        for (int i = 0; i < M; i++) {
            if (!visited[i]) {
                count++;
            }
        }
        System.out.println(count);
    }
}
