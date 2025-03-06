package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
*
* https://www.acmicpc.net/problem/2138
*
* */
public class baekjoon_2138 {

    private static void swap(boolean[] firstState, int index) {
        firstState[index] = !firstState[index];
    }

    private static int answer(boolean[] firstState, boolean[] ans) {
        int index = 1;
        int count = 0;
        boolean[] temp = firstState.clone();
        while (index < temp.length) {
            if (Arrays.equals(temp, ans)) {
                return count;
            }
            if (temp[index - 1] != ans[index - 1]) {
                swap(temp, index);
                if (index == temp.length - 1) {
                    swap(temp, index - 1);
                } else {
                    swap(temp, index - 1);
                    swap(temp, index + 1);
                }
                count++;
            }
            index++;
        }
        if (Arrays.equals(temp, ans)) {
            return count;
        } else {
            return -1;
        }
    }

    private static int answer2(boolean[] firstState, boolean[] ans) {
        int index = 1;
        int count = 1;
        boolean[] temp = firstState.clone();
        swap(temp, index);
        swap(temp, 0);
        while (index < temp.length) {
            if (Arrays.equals(temp, ans)) {
                return count;
            }
            if (temp[index - 1] != ans[index - 1]) {
                swap(temp, index);
                if (index == temp.length - 1) {
                    swap(temp, index - 1);
                } else {
                    swap(temp, index - 1);
                    swap(temp, index + 1);
                }
                count++;
            }
            index++;
        }

        if (Arrays.equals(temp, ans)) {
            return count;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        boolean[] firstState = new boolean[n];
        int index = 0;
        for (char c : br.readLine().toCharArray()) {
            firstState[index] = c != '0';
            index++;
        }

        boolean[] ans = new boolean[n];
        index = 0;
        for (char c : br.readLine().toCharArray()) {
            ans[index] = c != '0';
            index++;
        }
        int count1 = answer(firstState, ans);
        int count2 = answer2(firstState, ans);
        if (count1 == -1 & count2 == -1) {
            System.out.println(-1);
        } else if (count1 >= 0 && count2 >= 0) {
            System.out.println(Math.min(count1, count2));
        } else {
            System.out.println(Math.max(count1, count2));
        }
    }
}

