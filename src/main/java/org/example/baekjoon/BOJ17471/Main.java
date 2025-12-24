package org.example.baekjoon.BOJ17471;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] city = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            city[i] = Integer.parseInt(st.nextToken());
        }
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            List<Integer> list = new ArrayList<>();
            map.put(i, list);
            st = new StringTokenizer(br.readLine(), " ");
            int count = Integer.parseInt(st.nextToken());
            while (count-- > 0) {
                list.add(Integer.parseInt(st.nextToken()));
            }
        }

        List<Set<Integer>> combinations = getCombination(N);

        int minDiff = Integer.MAX_VALUE;
        for (Set<Integer> combination : combinations) {
            if (isDividedByTwoRegion(combination, map)) {
                int redRegion = 0;
                int blueRegion = 0;
                for (int i = 1; i <= N; i++) {
                    if (combination.contains(i)) {
                        redRegion += city[i];
                    } else {
                        blueRegion += city[i];
                    }
                }

                minDiff = Math.min(minDiff, Math.abs(redRegion - blueRegion));

            }
        }

        System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
    }

    private static boolean isDividedByTwoRegion(Set<Integer> combination, Map<Integer, List<Integer>> map) {
        boolean[] visited = new boolean[map.size() + 1];
        int flag = 0;
        for (int i = 1; i <= map.size(); i++) {
            if (combination.contains(i)) {
                flag = i;
                break;
            }
        }
        dfs(map, flag, visited, true, combination);
        for (int i = 1; i <= map.size(); i++) {
            if (!combination.contains(i)) {
                flag = i;
                break;
            }
        }
        dfs(map, flag, visited, false, combination);

        for (int i = 0; i < visited.length; i++) {
            if (i == 0) {
                continue;
            }
            boolean visit = visited[i];
            if (!visit) {
                return false;
            }
        }
        return true;
    }

    private static void dfs(Map<Integer, List<Integer>> map, int node, boolean[] visited, boolean contain, Set<Integer> combination) {
        visited[node] = true;
        List<Integer> edges = map.get(node);
        for (int edge : edges) {
            if (!visited[edge] && combination.contains(edge) == contain) {
                dfs(map, edge, visited, contain, combination);
            }
        }
    }

    private static List<Set<Integer>> getCombination(int N) {
        List<Set<Integer>> result = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            combinationHelper(result, i, new HashSet<>(), N, 1);
        }
        return result;
    }

    private static void combinationHelper(List<Set<Integer>> combination, int r, Set<Integer> current, int N, int node) {
        if (current.size() == r) {
            combination.add(new HashSet<>(current));
            return;
        }

        for (int i = node; i <= N; i++) {
            current.add(i);
            combinationHelper(combination, r, current, N, i + 1);
            current.remove(i);
        }
    }
}
