package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * https://www.acmicpc.net/problem/9241
 * */
public class baekjoon_9241 {
    static String before;
    static String after;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        before = br.readLine();
        after = br.readLine();
        int leftIndex = 0;
        int rightBeforeIndex = before.length() - 1;
        int rightAfterIndex = after.length() - 1;

        if (before.equals(after)) {
            System.out.println(0);
            return;
        }
        while (leftIndex < before.length() & leftIndex < after.length()) {
            if (before.charAt(leftIndex) != after.charAt(leftIndex)) {
                break;
            }
            leftIndex++;
        }

        while (rightBeforeIndex >= leftIndex & rightAfterIndex >= leftIndex) {
            if (before.charAt(rightBeforeIndex) != after.charAt(rightAfterIndex)) {
                break;
            }
            rightBeforeIndex--;
            rightAfterIndex--;
        }
        System.out.println(rightAfterIndex - leftIndex + 1);
    }
}