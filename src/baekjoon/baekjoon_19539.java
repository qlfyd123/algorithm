package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_19539 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int treeNum = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int treeHeightSum = 0;
        int treeaHeightRemain = 0;
        while (st.hasMoreTokens()) {
            int temp = Integer.parseInt(st.nextToken());
            treeaHeightRemain += temp / 2;
            treeHeightSum += temp;
        }

        if (treeNum != 1) {
            if (treeHeightSum % 3 == 0 && treeaHeightRemain >= treeHeightSum / 3) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        } else {
            if (treeHeightSum % 3 == 0) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}