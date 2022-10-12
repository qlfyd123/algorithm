package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
* https://www.acmicpc.net/problem/10975
* */
public class baekjoon_10975 {
    static class DequeValue implements Comparable<DequeValue> {
        int value;
        int index;

        private DequeValue(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public static DequeValue dequeValue(int value, int index) {
            return new DequeValue(value, index);
        }

        @Override
        public int compareTo(DequeValue dv) {
            return this.value - dv.value;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<DequeValue> dequeValue = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            dequeValue.add(DequeValue.dequeValue(Integer.parseInt(br.readLine()), i));
        }
        Collections.sort(dequeValue);
        int recentIndexValue = -1;
        boolean increase = false;
        int requireDeque = 0;
        int count = 0;
        for (DequeValue dv : dequeValue) {
            count++;
            if (recentIndexValue != -1) {
                if (recentIndexValue < dv.index) {
                    if (!increase) requireDeque++;
                    increase = true;
                } else {
                    if (count == N) requireDeque++;
                    increase = false;
                }
            }
            recentIndexValue = dv.index;
        }
        System.out.println(requireDeque);
    }
}