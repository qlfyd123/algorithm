package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <a href="https://www.acmicpc.net/problem/1005">BOJ1005 ACM CRAFT</a>
 */
public class BOJ1005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] times = new int[N + 1];
            List<ArrayList<Integer>> rules = new ArrayList<>();
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= N; i++) {
                times[i] = Integer.parseInt(st.nextToken());
                rules.add(new ArrayList<>());
            }
            rules.add(new ArrayList<>());
            int[] previousNode = new int[N + 1];
            while (K-- > 0) {
                st = new StringTokenizer(br.readLine(), " ");
                int pre = Integer.parseInt(st.nextToken());
                int next = Integer.parseInt(st.nextToken());
                rules.get(pre).add(next);
                previousNode[next]++;
            }
            int target = Integer.parseInt(br.readLine());
            ACM acm = new ACM(times, rules, target, previousNode);
            Queue<Integer> nodes = new LinkedList<>();
            for (int i = 1; i <= N; i++) {
                if (previousNode[i] == 0) {
                    nodes.add(i);
                }
            }
            while (!nodes.isEmpty()) {
                int node = nodes.poll();
                craft(node, acm, nodes);
            }
        }
    }

    static class ACM {
        private final int target;
        private final int[] previousNode;
        int[] times;
        List<ArrayList<Integer>> rules;
        int[] buildTime;

        public ACM(int[] times, List<ArrayList<Integer>> rules, int target, int[] previousNode) {
            buildTime = times.clone();
            this.previousNode = previousNode;
            this.times = times;
            this.rules = rules;
            this.target = target;
        }
    }

    private static void craft(int buildIndex, ACM acm, Queue<Integer> nodes) {
        if (buildIndex == acm.target) {
            System.out.println(acm.buildTime[buildIndex]);
        } else {
            for (int next : acm.rules.get(buildIndex)) {
                int nextBuildTime = acm.times[next] + acm.buildTime[buildIndex];
                if (acm.buildTime[next] < nextBuildTime) {
                    acm.buildTime[next] = nextBuildTime;
                }
                acm.previousNode[next]--;
                if (acm.previousNode[next] == 0) {
                    nodes.add(next);
                }
            }
        }
    }
}
