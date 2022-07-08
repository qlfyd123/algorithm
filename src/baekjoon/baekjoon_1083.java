package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class baekjoon_1083 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> A = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }

        int S = Integer.parseInt(br.readLine());
        int index = 0;
        while (S > 0 && index < A.size()) {
            int max = 0;
            for (int i = index; i < Math.min(index + S + 1, N); i++) {
                if (max < A.get(i))
                    max = A.get(i);
            }
            if (max != A.get(index)) {
                int maxValueIndex = A.indexOf(max);
                int indexValue = A.get(index);
                A.add(index, max);
                A.remove(maxValueIndex + 1);
                S -= maxValueIndex - index;
            }
            index++;
        }

        StringBuffer sb=new StringBuffer();
        for(int i:A){
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }
}