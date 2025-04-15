package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/4195">4195. 친구 네트워크</a>
 */
public class BOJ4195 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            StringBuilder sb = new StringBuilder();
            int n = Integer.parseInt(br.readLine());
            Map<String, Friend> index = new HashMap<>();
            while (n-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                String aName = st.nextToken();
                String bName = st.nextToken();

                Friend a = index.compute(aName, (k, v) -> v == null ? new Friend(k, null) : v);
                Friend b = index.compute(bName, (k, v) -> v == null ? new Friend(k, null) : v);

                Friend rootA = a.getRoot();
                Friend rootB = b.getRoot();

                if (rootB != rootA) {
                    if (rootB.networkSize > rootA.networkSize) {
                        rootB.parent = rootA;
                    } else {
                        rootA.parent = rootB;
                    }
                    rootA.networkSize = rootA.networkSize + rootB.networkSize;
                    rootB.networkSize = rootA.networkSize;
                }
                sb.append(rootA.networkSize).append("\n");
            }
            System.out.print(sb);
        }
    }

    private static class Friend {

        String name;
        int networkSize;
        Friend parent;

        public Friend(String name, Friend parent) {
            this.name = name;
            this.networkSize = 1;
            this.parent = parent;
        }

        public Friend getRoot() {
            if (parent == null) {
                return this;
            } else {
                this.parent = parent.getRoot();
                return this.parent;
            }
        }
    }
}
