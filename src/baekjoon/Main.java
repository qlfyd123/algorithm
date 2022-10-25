package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V;
    static int E;
    static int K;
    static Map<Integer, Node> nodeMap = new HashMap<>();
    static boolean[] closedNode;
    static int[] cost;

    static class Edge {
        int end;
        int weight;

        public Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int location;
        int cost = Integer.MAX_VALUE;
        List<Edge> edgeList = new ArrayList<>();

        public Node(int location) {
            this.location = location;
        }

        public void addEdge(Edge edge) {
            edgeList.add(edge);
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < V; i++) {
            nodeMap.put(i + 1, new Node(i + 1));
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            nodeMap.get(u).addEdge(new Edge(v, w));
        }
        closedNode = new boolean[V];
        nodeMap.get(K).cost = 0;
        closedNode[K] = true;
        Queue<Node> openNode = new PriorityQueue<>();
        openNode.add(nodeMap.get(K));
        for (int i = 0; i < V & !openNode.isEmpty(); i++) {
            Node currentNode = openNode.poll();
            for (Edge neighbor : currentNode.edgeList) {
                Node neighborNode = nodeMap.get(neighbor.end);
                neighborNode.cost = Math.min(neighborNode.cost, neighbor.weight + currentNode.cost);
                openNode.add(neighborNode);
            }
            closedNode[currentNode.location] = true;
        }
        for (Node node : nodeMap.values()) {
            int cost = node.cost;
            if (cost != Integer.MAX_VALUE) {
                System.out.println(cost);
            } else {
                System.out.println("INF");
            }
        }
    }
}