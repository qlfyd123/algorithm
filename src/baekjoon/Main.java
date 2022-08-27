package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Character, Queue<Integer>> number = new HashMap<>();
        int index = 0;
        Queue<Integer> numberQueue;
        for (char c : br.readLine().toCharArray()) {
            if (!number.containsKey(c)) {
                numberQueue = new PriorityQueue<>();
                numberQueue.add(index);
                number.put(c, numberQueue);
            } else {
                number.get(c).add(index);
            }
            index++;
        }
        char[] T = br.readLine().toCharArray();

        int count = 0;
        try {
            Queue<Integer> firstIndexQueue = number.get(T[0]);
            outer:
            while (!firstIndexQueue.isEmpty()) {
                int charIndex = -1;
                for (char c : T) {
                    if (charIndex == -1) {
                        charIndex = firstIndexQueue.poll();
                        continue;
                    }
                    Queue<Integer> indexQueue = number.get(c);
                    int indexTemp = 0;
                    while (indexTemp < charIndex) {
                        if (!indexQueue.isEmpty()) {
                            indexTemp = indexQueue.poll();
                        } else {
                            break outer;
                        }
                    }
                    charIndex = indexTemp;
                }
                count++;
            }
            System.out.println(count);
        } catch (NullPointerException e) {
            System.out.println(count);
        }
    }
}
