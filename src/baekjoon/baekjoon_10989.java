package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class baekjoon_10989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // ArrayList<Integer> al = new ArrayList<Integer>();

        int[] array = new int[N];

        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(array);

        StringBuffer sb = new StringBuffer();
        for (int i : array) {
            sb.append(i + "\n");
        }
        System.out.println(sb);
    }
}
