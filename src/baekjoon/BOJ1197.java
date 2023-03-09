package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @see <a href="https://www.acmicpc.net/problem/1197">BOJ1197 최소 스패닝 트리</a>
 */
public class BOJ1197 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        Queue<Edge> edges = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        while (E-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(start, end, cost));
        }
        Union union = new Union(V);
        int spanningTree = 0;
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            int startRoot = getRoot(edge.start, union.set);
            int endRoot = getRoot(edge.end, union.set);
            if (startRoot != endRoot) {
                union(startRoot, endRoot, union);
                spanningTree += edge.cost;
            }
        }
        System.out.println(spanningTree);
    }

    /**
     * @param x     그래프에 속한 원소
     * @param union 원소들의 집합을 표현한 배열
     * @return 임의의 원소 x가 속한 집합의 번호를 리턴합니다
     */
    public static int getRoot(int x, int[] union) {
        while (x != union[x]) {
            x = union[x];
        }
        return x;
    }

    /**
     * 두 원소 x,y가 속한 부분집합을 합칩니다.
     *
     * @param xRoot 집합의 번호1
     * @param yRoot 집합의 번호2
     * @param union 원소들의 집합을 표현한 배열
     */
    public static void union(int xRoot, int yRoot, Union union) {
        if (union.rank[xRoot] >= union.rank[yRoot]) {
            union.set[yRoot] = xRoot;
            union.rank[xRoot]++;
        } else {
            union.set[xRoot] = yRoot;
            union.rank[yRoot]++;
        }
    }

    private static class Edge {
        int end, cost, start;

        public Edge(int start, int end, int cost) {
            this.end = end;
            this.cost = cost;
            this.start = start;
        }
    }

    private static class Union {
        int[] set;
        int[] rank;

        public Union(int N) {
            set = new int[N + 1];
            for (int i = 1; i < N + 1; i++) {
                set[i] = i;
            }
            rank = new int[N + 1];
        }
    }
}
