package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
* https://www.acmicpc.net/problem/1464
* */
public class baekjoon_1464 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str = br.readLine().toCharArray();
        char flag = str[0];
        char[] clone = str.clone();
        Arrays.sort(clone);
        int min = clone[0];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < str.length; i++) {
            if (flag < str[i]) {
                sb.insert(0, str[i]);
            } else {
                sb.insert(i - 1, flag);
                flag = str[i];
            }
        }
        sb.append(flag);
        sb.reverse();
        System.out.println(sb);
    }
}
