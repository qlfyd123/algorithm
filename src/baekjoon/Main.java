package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V, E, K;
    static List<ArrayList<Edge>> graph;
    static int[] cost;
    static Queue<Edge> openNode = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
    static boolean[] closedNode;

    static class Edge {
        int end, weight;

        public Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    public static void addToNode(String edgeValues) {
        StringTokenizer st = new StringTokenizer(edgeValues);
        int u = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        graph.get(u - 1).add(new Edge(v - 1, w));
    }

    public static void dijkstra() {
        int curLocation = 0;
        cost[K - 1] = 0;
        openNode.add(new Edge(K - 1, 0));
        for (int i = 0; i < V & !openNode.isEmpty(); i++) {
            curLocation = openNode.poll().end;
            if (closedNode[curLocation]) {
                i--;
                continue;
            }
            int currNodeCost = cost[curLocation];
            for (Edge edge : graph.get(curLocation)) {
                if (edge.weight + currNodeCost < cost[edge.end]) {
                    cost[edge.end] = edge.weight + currNodeCost;
                    openNode.add(edge);
                }
            }
        }
        closedNode[curLocation] = true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        graph = new ArrayList<>() {
            {
                for (int i = 0; i < V; i++) {
                    add(i, new ArrayList<>());
                }
            }
        };
        for (int i = 0; i < E; i++)
            addToNode(br.readLine());
        cost = new int[V];
        Arrays.fill(cost, Integer.MAX_VALUE);
        closedNode = new boolean[V];
        Arrays.fill(closedNode, false);

        dijkstra();
        for (int i = 0; i < V; i++) {
            int ans = cost[i];
            if (ans == Integer.MAX_VALUE)
                System.out.println("INF");
            else
                System.out.println(cost[i]);
        }
    }
}