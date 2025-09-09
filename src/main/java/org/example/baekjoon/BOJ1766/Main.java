package org.example.baekjoon.BOJ1766;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Problem[] problems = new Problem[N + 1];
        for (int i = 0; i <= N; i++) {
            problems[i] = new Problem(i);
        }

        boolean[] isParent = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int problemNumber = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken());

            Problem child = problems[problemNumber];
            Problem parent = problems[prev];

            parent.children++;

            child.parents.add(parent.num);
            isParent[parent.num] = true;
        }
        Queue<Problem> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.children == o2.children) return o1.num - o2.num;
            else return o1.children - o2.children;
        });

        for (int i = 1; i < problems.length; i++) {
            Problem p = problems[i];
            if (!isParent[p.num]) queue.add(p);
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Problem p = queue.poll();
            if (p.children == 0) {
                sb.append(p.num).append(" ");
                for (int parent : p.parents) {
                    problems[parent].children--;
                    if (problems[parent].children == 0) {
                        queue.add(problems[parent]);
                    }
                }
            }
        }

        System.out.println(sb);
    }

    private static class Problem {
        int num;
        int children;
        List<Integer> parents;

        public Problem(int num) {
            this.num = num;
            children = 0;
            parents = new ArrayList<>();
        }
    }
}
