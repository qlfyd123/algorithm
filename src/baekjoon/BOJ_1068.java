package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class BOJ_1068 {
    private static class Node {
        List<Integer> children;

        Node() {
            children = new ArrayList<>();
        }

        void addChild(int child) {
            children.add(child);
        }
    }

    static int N;
    static Node[] nodes;
    static boolean[] erased;
    static int eraseNode;

    public static void erase(int node) {
        erased[node] = true;
        for (int child : nodes[node].children) {
            erase(child);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N];
        erased = new boolean[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node();
        }
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                continue;
            }
            Node parendNode = nodes[parent];
            parendNode.addChild(i);
        }
        eraseNode = Integer.parseInt(br.readLine());
        erase(eraseNode);
        int count = getReefNodeCount();
        System.out.println(count);
    }

    private static int getReefNodeCount() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (erased[i]) {
                continue;
            }
            if (nodes[i].children.isEmpty()) {
                count++;
            } else {
                boolean isLeaf = true;
                for (int child : nodes[i].children) {
                    if (!erased[child]) {
                        isLeaf = false;
                        break;
                    }
                }
                if (isLeaf) {
                    count++;
                }
            }
        }
        return count;
    }
}
