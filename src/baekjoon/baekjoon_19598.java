package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class baekjoon_19598 {
    static class Meeting implements Comparable<Meeting> {
        final int startTime;
        final int endTime;

        private Meeting(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public static Meeting meetingOf(int startTime, int endTime) {
            return new Meeting(startTime, endTime);
        }

        @Override
        public int compareTo(Meeting meeting) {
            if (meeting.startTime == this.startTime) {
                return this.endTime - meeting.endTime;
            } else {
                return this.startTime - meeting.startTime;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int meetingNum = Integer.parseInt(br.readLine());
        List<Meeting> meetings = new ArrayList<>();
        StringTokenizer st;
        while (meetingNum-- > 0) {
            st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            meetings.add(Meeting.meetingOf(startTime, endTime));
        }

        Collections.sort(meetings);

        Queue<Integer> meetingEndtimeQueue = new PriorityQueue<>();

        for (Meeting meeting : meetings) {
            if (!meetingEndtimeQueue.isEmpty() && meeting.startTime >= meetingEndtimeQueue.peek()) {
                meetingEndtimeQueue.poll();
            }
            meetingEndtimeQueue.add(meeting.endTime);
        }

        System.out.println(meetingEndtimeQueue.size());
    }
}
