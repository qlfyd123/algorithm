package org.example.baekjoon.BOJ17609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            char[] string = reader.readLine().toCharArray();
            int palindromeType = isPalindrome(string, 0, string.length - 1);

            sb.append(palindromeType).append("\n");
        }
        System.out.println(sb);
    }

    private static int isPalindrome(char[] str, int start, int end) {
        while (start < end) {
            if (str[start] != str[end]) {
                boolean isSimilarPalindrome = isSimilarPalindrome(str, start + 1, end);
                if (isSimilarPalindrome) {
                    return 1;
                }
                isSimilarPalindrome = isSimilarPalindrome(str, start, end - 1);
                if (isSimilarPalindrome) {
                    return 1;
                } else {
                    return 2;
                }
            }
            start++;
            end--;
        }
        return 0;
    }

    private static boolean isSimilarPalindrome(char[] str, int start, int end) {
        while (start < end) {
            if (str[start] != str[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
