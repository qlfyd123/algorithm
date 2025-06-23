package org.example.baekjoon.BOJ1700;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] sequence = new int[K];
        st = new StringTokenizer(br.readLine(), " ");
        Map<Integer, Queue<Integer>> stackHashMap = new HashMap<>();
        for (int i = 1; i <= K; i++) {
            stackHashMap.put(i, new PriorityQueue<>());
        }
        for (int i = 0; i < K; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
            stackHashMap.get(sequence[i]).add(i);
        }

        Set<Integer> plugs = new HashSet<>();
        int count = 0;
        for (int i = 0; i < K; i++) {
            int current = sequence[i];
            if (plugs.size() == N) {
                if (!plugs.contains(current)) {
                    int plug = findLastlyUsePlug(plugs, stackHashMap);
                    plugs.remove(plug);
                    count++;
                    plugs.add(current);
                    if (!stackHashMap.get(current).isEmpty()) {
                        stackHashMap.get(current).poll();
                    }
                } else {
                    stackHashMap.get(current).poll();
                }
            } else {
                plugs.add(current);
                stackHashMap.get(current).poll();
            }
        }
        System.out.println(count);
    }

    private static int findLastlyUsePlug(Set<Integer> plugs, Map<Integer, Queue<Integer>> stackHashMap) {
        int maxIndex = -1;
        int plugToRemove = -1;
        for (int plug : plugs) {
            Queue<Integer> queue = stackHashMap.get(plug);
            if (queue.isEmpty()) {
                return plug;
            } else {
                int index = queue.peek();
                if (index > maxIndex) {
                    maxIndex = index;
                    plugToRemove = plug;
                }
            }
        }
        return plugToRemove;
    }
}
