package baekjoon;


/*
* 소가 도착하는 시간과 검문에 소요되는 시간이 주어질때
* 모든 소가 검문을 받고 농장에 입장할때까지 걸리는 시간을 출력
* */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class baekjoon_14469 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] cow = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                cow[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(cow, Comparator.comparingInt(o -> o[0]));

        int time = 0;

        for (int[] i : cow) {
            if (time < i[0]) {
                time = i[0];
            }
            time += i[1];
        }

        System.out.println(time);
    }
}