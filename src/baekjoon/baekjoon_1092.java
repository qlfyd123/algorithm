package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class baekjoon_1092 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Integer[] crainWeight = new Integer[N];

        int maxCrainWeight = 0;
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            crainWeight[i] = temp;

            if (maxCrainWeight < temp) maxCrainWeight = temp;
        }

        Arrays.sort(crainWeight,Collections.reverseOrder());

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> boxQueue = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int temp = Integer.parseInt(st.nextToken());

            if (maxCrainWeight < temp) {
                System.out.println(-1);
                return;
            }

            boxQueue.add(temp);
        }

        boxQueue.sort(Collections.reverseOrder());

        int result = 0;
        while (!boxQueue.isEmpty()) {
            int index = 0;
            for (int i = 0; i < crainWeight.length; ) {
                if(index==boxQueue.size()){
                    break;
                }
                int crain = crainWeight[i];
                int boxWeight = boxQueue.get(index);
                if (crain >= boxWeight) {
                    boxQueue.remove(index);
                    i++;
                } else {
                    index++;
                }
            }
            result++;
        }
        System.out.println(result);
    }
}
