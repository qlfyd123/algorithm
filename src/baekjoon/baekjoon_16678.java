package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.PriorityQueue;
import java.util.Queue;
/*
*
* https://www.acmicpc.net/problem/16678
*
* */
public class baekjoon_16678 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Queue<Integer> honorQueue = new PriorityQueue<>();
        while (N-- > 0) {
            honorQueue.add(Integer.parseInt(br.readLine()));
        }
        BigInteger defile = new BigInteger(String.valueOf(Integer.valueOf(0)));
        int index = 1;
        while (!honorQueue.isEmpty()) {
            int honor = honorQueue.poll();
            int declineHonor = honor - index;
            if (declineHonor == 0) {
                index++;
            } else if (declineHonor > 0) {
                defile = defile.add(BigInteger.valueOf(Long.parseLong(String.valueOf(declineHonor))));
                index++;
            }
        }

        System.out.println(defile);
    }
}
