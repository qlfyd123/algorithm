package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int buildTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            buildTime = 0;
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
            ACM acm = new ACM(N, times, rules);
            for (int i = 1; i <= N; i++) {
                if (!hasRule[i]) {
                    acm.buildTime[i] = times[i];
                    craft(i, acm);
                }
            }
            System.out.println(acm.buildTime[target]);
        }
    }

    static class ACM {
        int[] times;
        List<ArrayList<Integer>> rules;
        int[] buildTime;

        public ACM(int N, int[] times, List<ArrayList<Integer>> rules) {
            buildTime = new int[N + 1];
            this.times = times;
            this.rules = rules;
        }
    }

    private static void craft(int buildIndex, ACM acm) {
        for (int next : acm.rules.get(buildIndex)) {
            if (acm.buildTime[next] < acm.buildTime[buildIndex] + acm.times[next]) {
                acm.buildTime[next] = acm.buildTime[buildIndex] + acm.times[next];
                craft(next, acm);
            }
        }
    }
}
