package org.example.baekjoon;

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;
/**
 *  <a href="https://www.acmicpc.net/problem/7662">BOJ7662 이중 우선순위 큐</a>
 * */
public class BOJ7662 {

    private final static int REMOVE_MAX = 1;
    private final static int REMOVE_MIN = -1;

    private static class Number implements Comparable<Number> {
        long num;

        public Number(long num) {
            this.num = num;
        }

        @Override
        public int compareTo(Number o) {
            if (num == o.num) {
                return -1;
            } else {
                if (num - o.num > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            int T = Integer.parseInt(br.readLine());
            TreeSet<Number> treeSet = new TreeSet<>();
            for (int i = 0; i < T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                String type = st.nextToken();
                switch (type) {
                    case "I":
                        long num = Long.parseLong(st.nextToken());
                        treeSet.add(new Number(num));
                        break;
                    case "D":
                        int operation = Integer.parseInt(st.nextToken());
                        if (operation == REMOVE_MAX) {
                            treeSet.pollLast();
                        } else if (operation == REMOVE_MIN) {
                            treeSet.pollFirst();
                        }
                        break;
                }
            }
            if (treeSet.isEmpty()) {
                bw.write("EMPTY\n");
            } else {
                bw.write(treeSet.last().num + " " + treeSet.first().num + "\n");
            }
            bw.flush();
        }
        bw.close();
        br.close();
    }
}
