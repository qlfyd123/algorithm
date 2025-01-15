package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            } else {
                return o1[1] - o2[1];
            }
        });
        for (int i = 0; i < N; i++) {
            int start, end;
            StringTokenizer st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            queue.add(new int[]{start, end});
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        while (!queue.isEmpty()) {
            int[] meeting = queue.poll();
            int start, end;
            start = meeting[0];
            end = meeting[1];
            int eveMeetingCount = map.floorEntry(start) == null ? 0 : map.floorEntry(start).getValue();
            int latestMeetingCount = map.floorEntry(end) == null ? 0 : map.floorEntry(end).getValue();
            int meetingCount = Math.max(eveMeetingCount + 1, latestMeetingCount);
            map.put(end, meetingCount);
        }

        System.out.println(map.lastEntry().getValue());
    }
}