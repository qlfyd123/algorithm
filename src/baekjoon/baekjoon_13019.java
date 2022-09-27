package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class baekjoon_13019 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String A = br.readLine();
        String B = br.readLine();
        int count = 0;
        int indexA = A.length() - 1;
        int indexB = B.length() - 1;
        Queue<Integer> AQueue = new PriorityQueue<>();
        A.chars().forEach(AQueue::add);
        Queue<Integer> BQueue = new PriorityQueue<>();
        B.chars().forEach(BQueue::add);
        while (!AQueue.isEmpty() && !BQueue.isEmpty()) {
            int temp = AQueue.poll();
            int temp2 = BQueue.poll();
            if (temp != temp2) {
                System.out.println(-1);
                return;
            }
        }
        while (indexA >= 0) {
            if (A.charAt(indexA) == B.charAt(indexB)) {
                indexB--;
                indexA--;
            } else {
                while (indexA >= 0 && A.charAt(indexA) != B.charAt(indexB)) {
                    indexA--;
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
