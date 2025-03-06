package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9019 {
    /**
     * @see <a href="https://www.acmicpc.net/problem/9019">백준 9019 DSLR</a>
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int before = Integer.parseInt(st.nextToken());
            int after = Integer.parseInt(st.nextToken());
            Queue<DSLR> testCases = new LinkedList<>();
            testCases.add(new DSLR(before));
            boolean[] visited = new boolean[10000];
            while (!testCases.isEmpty()) {
                DSLR tc = testCases.poll();
                if (tc.num == after) {
                    System.out.println(tc.string);
                    break;
                } else if (visited[tc.num]) continue;
                tc.D(testCases, visited);
                tc.S(testCases, visited);
                tc.L(testCases, visited);
                tc.R(testCases, visited);
                visited[tc.num] = true;
            }
        }
    }

    private static class DSLR {
        int num;
        String string;

        /**
         * @param num DSLR연산에 사용되는 숫자
         */
        public DSLR(int num) {
            this.num = num;
            string = "";
        }

        /**
         * @param num DSLR연산에 사용되는 숫자
         * @param str 현재까지 진행된 연산 순서를 저장한 문자열
         */
        public DSLR(int num, String str) {
            this.num = num;
            this.string = str;
        }

        /**
         * @param testCases BFS에 사용되는 큐
         * @param visited   해당 숫자에서 DSLR연산을 진행했는지를 저장하는 배열
         */
        public void D(Queue<DSLR> testCases, boolean[] visited) {
            int num = this.num << 1;
            if (num >= 10000) {
                num -= 10000;
            }
            if (!visited[num]) {
                testCases.add(new DSLR(num, this.string + "D"));
            }
        }

        public void S(Queue<DSLR> testCases, boolean[] visited) {
            int num;
            if (this.num == 0) {
                num = 9999;
            } else {
                num = this.num - 1;
            }
            if (!visited[num]) {
                testCases.add(new DSLR(num, this.string + "S"));
            }
        }

        public void L(Queue<DSLR> testCases, boolean[] visited) {
            int num = this.num;
            int l = num / 1000;
            num -= 1000 * l;
            num *= 10;
            num += l;
            if (!visited[num]) {
                testCases.add(new DSLR(num, this.string + "L"));
            }
        }

        public void R(Queue<DSLR> testCases, boolean[] visited) {
            int num = this.num;
            int r = num % 10;
            num /= 10;
            num += r * 1000;
            if (!visited[num]) {
                testCases.add(new DSLR(num, this.string + "R"));
            }
        }
    }
}
