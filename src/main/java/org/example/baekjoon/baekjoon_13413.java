package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_13413 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            int length = Integer.parseInt(br.readLine());
            char[] before = br.readLine().toCharArray();
            char[] after = br.readLine().toCharArray();

            int result = 0;
            int diff = 0;
            int beforeCount = 0;
            int afterCount = 0;
            for (int i = 0; i < length; i++) {
                if (before[i] != after[i]) {
                    diff++;
                }
                if (before[i] == 'W') {
                    beforeCount++;
                }
                if (after[i] == 'W') {
                    afterCount++;
                }
            }

            while (beforeCount != afterCount) {
                if (beforeCount > afterCount) {
                    beforeCount--;
                } else {
                    afterCount--;
                }
                diff--;
                result++;
            }

            result+=diff/2;

            System.out.println(result);
        }
    }
}