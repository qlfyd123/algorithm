package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/17298">BOJ17298 오큰수</a>
 * */
public class BOJ17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] NGE = new int[N];
        Stack<Number> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            if (stack.isEmpty()) {
                stack.push(new Number(Integer.parseInt(st.nextToken()), i));
            } else {
                int num = Integer.parseInt(st.nextToken());
                while (!stack.isEmpty()) {
                    if (stack.peek().num < num) {
                        Number number = stack.pop();
                        NGE[number.index] = num;
                    } else {
                        break;
                    }
                }
                stack.push(new Number(num, i));
            }
        }

        while (!stack.isEmpty()) {
            Number number = stack.pop();
            NGE[number.index] = -1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(NGE[i]).append(" ");
        }
        System.out.println(sb);
    }

    static class Number {
        final int num;
        final int index;

        Number(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }
}
