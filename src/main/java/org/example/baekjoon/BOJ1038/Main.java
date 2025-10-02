package org.example.baekjoon.BOJ1038;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {

    private final static long MAX_NUMBER = 9876543210L;
    private final static int NINE = 9;
    private final static int ZERO = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long number = 0;
        for (int i = 0; i < N; i++) {
            number = findNextDecreasingNumber(number);
            if (number == -1) {
                break;
            }
        }
        if (number > MAX_NUMBER || number == -1) {
            System.out.println(-1);
        } else {
            System.out.println(number);
        }
    }

    private static long findNextDecreasingNumber(long number) {
        List<Integer> separated = separateEachNumber(number);
        increaseNumber(separated.size() - 1, separated);
        if (separated.size() > 10) {
            return -1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i : separated) {
            sb.append(i);
        }
        return Long.parseLong(sb.toString());
    }

    private static void increaseNumber(int index, List<Integer> separated) {
        if (index == 0) {
            if (separated.get(index) == NINE) {
                int prevSize = separated.size();
                separated.clear();
                for (int i = prevSize; i >= 0; i--) {
                    separated.add(i);
                }
            } else {
                separated.set(index, (separated.get(index) + 1));
            }
        } else {
            if (separated.get(index) < separated.get(index - 1) - 1) {
                separated.set(index, (separated.get(index) + 1));
            } else {
                separated.set(index, separated.size() - index - 1);
                increaseNumber(index - 1, separated);
            }
        }
    }

    private static List<Integer> separateEachNumber(long number) {
        List<Integer> separated = new LinkedList<>();
        if (number == 0) {
            separated.add(ZERO);
            return separated;
        }

        while (number > 0) {
            separated.add(0, (int) (number % 10));
            number /= 10;
        }

        return separated;
    }
}
