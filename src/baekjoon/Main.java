package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class ClassTable implements Comparable<ClassTable> {
        final int start;
        final int end;

        private ClassTable(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public static ClassTable of(int start, int end) {
            return new ClassTable(start, end);
        }

        @Override
        public int compareTo(ClassTable c) {
            if (this.start == c.start) {
                return this.end - c.end;
            } else {
                return this.start - c.start;
            }
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<ClassTable> lecture = new ArrayList<>();

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            lecture.add(ClassTable.of(start, end));
        }

        Collections.sort(lecture);
        Queue<Integer> timeTable = new LinkedList<>();
        for (ClassTable c : lecture) {
            if (!timeTable.isEmpty() && c.start >= timeTable.peek()) {
                timeTable.poll();
            }
            timeTable.add(c.end);
        }
        System.out.println(timeTable.size());
    }
}
