package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/**
 * @see <a href="https://www.acmicpc.net/problem/5430">백준5430</a>
 * */
public class baekjoon_5430 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            ArrayList<Boolean> process = new ArrayList<>();
            boolean left = true;
            int count = 0;
            for (char c : br.readLine().toCharArray()) {
                if (c == 'D') {
                    process.add(left);
                } else {
                    left = !left;
                    count++;
                }
            }
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
            Deque<String> string = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                string.add(st.nextToken());
            }
            try {
                for (boolean deleteLeft : process) {
                    if (deleteLeft & !string.isEmpty()) {
                        string.pollFirst();
                    } else if (!deleteLeft & !string.isEmpty()) {
                        string.pollLast();
                    } else {
                        throw new RuntimeException();
                    }
                }
            } catch (RuntimeException e) {
                System.out.println("error");
                continue;
            }
            StringJoiner sj = new StringJoiner(",", "[", "]");
            if (count % 2 == 0) {
                while (!string.isEmpty())
                    sj.add(string.pollFirst());
            } else {
                while (!string.isEmpty())
                    sj.add(string.pollLast());
            }

            System.out.println(sj);
        }
    }
}
