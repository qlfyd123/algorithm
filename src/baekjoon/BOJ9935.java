package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * <a href="https://www.acmicpc.net/problem/9935">BOJ 9935 문자열 폭발</a>
 * */
public class BOJ9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String string = br.readLine();
        String bombString = br.readLine();
        StringBuilder newString = new StringBuilder();
        for (char c : string.toCharArray()) {
            newString.append(c);
            if (newString.length() >= bombString.length()) {
                if (newString.substring(newString.length() - bombString.length(), newString.length()).equals(bombString)) {
                    newString.delete(newString.length() - bombString.length(), newString.length());
                }
            }
        }
        System.out.println(newString.toString().equals("") ? "FRULA" : newString);
    }
}
