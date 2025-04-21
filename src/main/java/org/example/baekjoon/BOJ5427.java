package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/5427">5427. 불</a>
 * */
public class BOJ5427 {

    private final static int[] dx = {-1, 1, 0, 0};
    private final static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        TestCase:
        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            String[][] map = new String[r][c];
            Entry<String, int[]> pos = null;
            Set<Entry<String, int[]>> fire = new HashSet<>();
            for (int i = 0; i < r; i++) {
                String[] input = br.readLine().split("");
                map[i] = input;
                for (int j = 0; j < c; j++) {
                    String token = input[j];
                    if (token.equals("@")) {
                        pos = new SimpleEntry<>("@", new int[]{i, j});
                    } else if (token.equals("*")) {
                        Entry<String, int[]> entry = new SimpleEntry<>("*", new int[]{i, j});
                        fire.add(entry);
                    }

                }
            }
            Queue<Entry<String, int[]>> queue = new LinkedList<>(fire);
            queue.add(pos);
            queue.add(new SimpleEntry<>("CycleFlag", null));
            int cycle = 0;
            while (!queue.isEmpty()) {
                Entry<String, int[]> entry = queue.poll();
                String token = entry.getKey();

                if (token.equals("CycleFlag")) {
                    cycle++;
                    if (!queue.isEmpty()) {
                        queue.add(entry);
                    }
                    continue;
                }

                int[] current = entry.getValue();
                for (int j = 0; j < 4; j++) {
                    int nx = current[0] + dx[j];
                    int ny = current[1] + dy[j];
                    if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                        if (token.equals("@")) {
                            System.out.println(cycle + 1);
                            continue TestCase;
                        }
                    } else {
                        if (token.equals("*")) {
                            if (map[nx][ny].equals("@") || map[nx][ny].equals(".")) {
                                map[nx][ny] = "*";
                                queue.add(new SimpleEntry<>("*", new int[]{nx, ny}));
                            }
                        } else if (token.equals("@")) {
                            if (map[nx][ny].equals(".")) {
                                map[nx][ny] = "@";
                                queue.add(new SimpleEntry<>("@", new int[]{nx, ny}));
                            }
                        }
                    }
                }
            }
            System.out.println("IMPOSSIBLE");

        }
    }
}
