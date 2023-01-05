package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * @see <a href="https://www.acmicpc.net/problem/1707">백준1707</a>
 * */
public class baekjoon_1707 {

    static class TestCase {
        private final int E, V;
        private final ArrayList<Queue<Integer>> edges;
        private final int[] coloredNode;
        private final BufferedReader br;

        public TestCase(int V, int E, BufferedReader br) {
            edges = new ArrayList<>();
            coloredNode = new int[V + 1];
            this.E = E;
            this.V = V;
            for (int i = 0; i < V + 1; i++) {
                edges.add(new LinkedList<>());
            }
            this.br = br;
        }

        public void getEdge() throws IOException {
            for (int i = 0; i < E; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                edges.get(left).add(right);
                edges.get(right).add(left);
            }
        }

        private boolean search(boolean color, int nodeNo) {
            if (nodeNo >= coloredNode.length) return true;
            coloredNode[nodeNo] = color ? 1 : 2;
            color = !color;
            Queue<Integer> node = edges.get(nodeNo);
            if (node.size() == 0) {
                return search(color, nodeNo + 1);
            } else {
                while (!node.isEmpty()) {
                    int edge = node.poll();
                    if (coloredNode[edge] == 0) {
                        if (!search(color, edge)) {
                            return false;
                        }
                    } else {
                        if (coloredNode[edge] == coloredNode[nodeNo]) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        public String ans() {
            for (int i = 1; i < V; i++) {
                if (coloredNode[i] == 0) {
                    if (!search(true, i)) {
                        return "NO";
                    }
                }
            }
            return "YES";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            TestCase testCase = new TestCase(V, E, br);
            testCase.getEdge();
            System.out.println(testCase.ans());
        }
    }
}