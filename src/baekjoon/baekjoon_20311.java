package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;
/*
* https://www.acmicpc.net/problem/20311
* */
public class baekjoon_20311 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int examinerNum = Integer.parseInt(st.nextToken());
        int[] color = new int[Integer.parseInt(st.nextToken()) + 1];

        st = new StringTokenizer(br.readLine());
        int max = 0;
        int maxIndex = 0;
        for (int i = 1; i < color.length; i++) {
            int temp = Integer.parseInt(st.nextToken());
            if (temp > max) {
                max = temp;
                maxIndex = i;
            }
            color[i] = temp;
        }

        if (max > (examinerNum + 1) / 2) {
            System.out.println(-1);
            return;
        }
        List<Queue<Integer>> examiner = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            Queue<Integer> group = new PriorityQueue<>();
            group.add(maxIndex);
            examiner.add(group);
        }
        examinerNum -= max;
        int index = 1;
        int i = 0;
        int limit = color[maxIndex];
        while (examinerNum-- > 0) {
            if (index == maxIndex || color[index] == 0) {
                index++;
                examinerNum++;
                continue;
            }
            examiner.get(i).add(index);
            color[index]--;
            if (i == limit - 1) {
                i = 0;
            } else {
                i++;
            }
        }

        StringJoiner sj = new StringJoiner(" ");
        examiner.stream()
                .flatMap(queue -> Stream.generate(queue::poll)
                        .limit(queue.size()))
                .forEach(queueValue -> sj.add(String.valueOf(queueValue)));
        System.out.println(sj);
    }
}
