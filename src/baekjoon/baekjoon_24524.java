package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class baekjoon_24524 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Character, Queue<Integer>> numberIndexMap = new HashMap<>();
        int index = 0;
        Queue<Integer> numberIndexQueue;
        for (char c : br.readLine().toCharArray()) {
            if (!numberIndexMap.containsKey(c)) {
                numberIndexQueue = new PriorityQueue<>();
                numberIndexQueue.add(index);
                numberIndexMap.put(c, numberIndexQueue);
            } else {
                numberIndexMap.get(c).add(index);
            }
            index++;
        }
        char[] T = br.readLine().toCharArray();

        int makeStringCount = 0;
        int charIndex = -1;
        try {
            while (true) {
                for (char c : T) {
                    Queue<Integer> indexQueue = numberIndexMap.get(c);
                    if (charIndex == -1) {
                        charIndex = indexQueue.poll();
                    } else {
                        int charIndexTemp = indexQueue.poll();
                        while (charIndexTemp < charIndex) {
                            charIndexTemp = indexQueue.poll();
                        }
                        charIndex = charIndexTemp;
                    }
                }
                charIndex = -1;
                makeStringCount++;
            }
        } catch (NullPointerException e) {
            System.out.println(makeStringCount);
        }
    }
}
