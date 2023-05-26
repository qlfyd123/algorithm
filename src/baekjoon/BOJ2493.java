package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/2493">BOJ2493 탑</a>
 * */
public class BOJ2493 {
    static class Tower implements Comparable<Tower> {
        int height, index;

        public Tower(int height, int index) {
            this.height = height;
            this.index = index;
        }

        @Override
        public int compareTo(Tower o) {
            if (this.height == o.height) {
                return this.index - o.index;
            }
            return o.height - this.height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        List<Tower> towers = new ArrayList<>();
        int[] razor = new int[N + 1];
        for (int i = 1; i <= N; i++)
            towers.add(new Tower(Integer.parseInt(st.nextToken()), i));
        Stack<Tower> stack = new Stack<>();
        for (int i = towers.size() - 1; i >= 0; i--) {
            Tower tower = towers.get(i);
            while (!stack.isEmpty()) {
                Tower comparingTower = stack.peek();
                if (comparingTower.height < tower.height) {
                    razor[comparingTower.index] = tower.index;
                    stack.pop();
                } else {
                    break;
                }
            }
            stack.push(tower);
        }
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < razor.length; i++) {
            result.append(razor[i]).append(" ");
        }
        System.out.println(result);
    }
}
