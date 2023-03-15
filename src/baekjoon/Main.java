package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
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
            boolean[] hasRule = new boolean[N + 1];
            while (K-- > 0) {
                st = new StringTokenizer(br.readLine(), " ");
                int pre = Integer.parseInt(st.nextToken());
                int next = Integer.parseInt(st.nextToken());
                rules.get(pre).add(next);
                hasRule[next] = true;
            }
            int target = Integer.parseInt(br.readLine());
            ACM acm = new ACM(N, times, rules, target);
            int BuildTime = 0;
            for (int i = 1; i <= N; i++) {
                if (!hasRule[i]) {
                    acm.buildTime[i] = times[i];
                    craft(i, acm);
                    BuildTime = Math.max(BuildTime, acm.buildTime[i]);
                }
            }
            System.out.println(BuildTime);
        }
    }

    static class ACM {
        private final int target;
        int[] times;
        List<ArrayList<Integer>> rules;
        int[] buildTime;

        public ACM(int N, int[] times, List<ArrayList<Integer>> rules, int target) {
            buildTime = new int[N + 1];
            buildTime[target] = times[target];
            this.times = times;
            this.rules = rules;
            this.target = target;
        }
    }

    private static void craft(int buildIndex, ACM acm) {
        if (buildIndex == acm.target) {
            return;
        }
        int minBuildTime = 0;
        for (int next : acm.rules.get(buildIndex)) {
            craft(next, acm);
            minBuildTime = Math.max(minBuildTime, acm.buildTime[next]);
        }
        acm.buildTime[buildIndex] = acm.times[buildIndex] + minBuildTime;
    }
}
