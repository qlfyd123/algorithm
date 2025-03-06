package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
* @see <a href="https://www.acmicpc.net/problem/1717">백준1717</a>
* */
public class baekjoon_1717 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] unit = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            unit[i] = i;
        }
        int m = Integer.parseInt(st.nextToken());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            switch (type) {
                case 0:
                    addUnit(x, y, unit);
                    break;
                case 1:
                    if (isSameUnit(x, y, unit))
                        System.out.println("yes");
                    else
                        System.out.println("no");
                    break;
            }
        }
    }

    private static int findRoot(int value, int[] unit) {
        return value == unit[value] ? value : findRoot(unit[value], unit);
    }

    private static boolean isSameUnit(int x, int y, int[] unit) {
        int xRoot = findRoot(x, unit);
        int yRoot = findRoot(y, unit);
        return xRoot == yRoot;
    }

    private static void addUnit(int x, int y, int[] unit) {
        int xRoot = findRoot(x, unit);
        int yRoot = findRoot(y, unit);

        if (xRoot > yRoot) {
            unit[yRoot] = xRoot;
        } else if (xRoot < yRoot) {
            unit[xRoot] = yRoot;
        }
    }
}
