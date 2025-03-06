package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon_1374 {
    static class Lecture implements Comparable<Lecture> {
        final int lectureNum;
        int startTime;
        int endTime;

        private Lecture(int lectureNum, int startTime, int endTime) {
            this.lectureNum = lectureNum;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public static Lecture lectureOf(int lectureNum, int startTime, int endTime) {
            return new Lecture(lectureNum, startTime, endTime);
        }

        @Override
        public int compareTo(Lecture lecture) {

            if (this.startTime == lecture.startTime) {
                return this.endTime - lecture.endTime;
            } else {
                return this.startTime - lecture.startTime;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Lecture> classList = new ArrayList<>();

        StringTokenizer st;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int lectureNum = Integer.parseInt(st.nextToken());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            classList.add(Lecture.lectureOf(lectureNum, startTime, endTime));
        }

        Collections.sort(classList);
        Queue<Integer> timeTable = new PriorityQueue <>();
        for (Lecture lecture : classList) {
            if (!timeTable.isEmpty()&&timeTable.peek()<=lecture.startTime){
                timeTable.poll();
            }
            timeTable.add(lecture.endTime);
        }

        System.out.println(timeTable.size());
    }
}
