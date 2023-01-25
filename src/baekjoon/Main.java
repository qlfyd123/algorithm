package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
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

    private static boolean isSameUnit(int x, int y, int[] unit) {
        while (unit[x] != x) {
            if (unit[x] == y) {
                return true;
            }
            x = unit[x];
        }

        while (unit[y] != y) {
            y = unit[y];
        }
        return x == y;
    }

    private static void addUnit(int x, int y, int[] unit) {
        int big = Math.max(x, y);
        int small = Math.min(x, y);
        while (unit[big] != big) {
            big = unit[big];
            if (y == big | x == big) {
                return;
            }
        }

        if (!(small == unit[small] & small < big)) {
            while (unit[small] != small) {
                small = unit[small];
                if (y == small | x == small) {
                    return;
                }
            }
            if (big < small) {
                unit[small] = big;
            } else unit[big] = small;
        } else {
            unit[big] = small;
        }
    }
}
