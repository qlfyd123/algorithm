package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baekjoon_12034 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        int loop = 1;
        while (testCase-- > 0) {
            int N = Integer.parseInt(br.readLine()) * 2;

            StringTokenizer st = new StringTokenizer(br.readLine());

            LinkedList<Long> al = new LinkedList<Long>();
            while (N-- > 0) {
                Long price = Long.parseLong(st.nextToken());
                al.add(price);
            }

            Collections.sort(al);

            System.out.print("Case #" + loop + ": ");
            while (al.size() > 2) {

                long price = al.getFirst();

                System.out.print(price + " ");
                al.removeFirst();
                al.remove(Long.valueOf(price * 100 / 75));

            }
            System.out.println(al.getFirst());
            loop++;
        }
    }
}