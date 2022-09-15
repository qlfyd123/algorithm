package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/*
*https://www.acmicpc.net/problem/23254
* */
public class baekjoon_23254 {
    static class Score implements Comparable<Score> {
        int minScore;
        int increase;

        public Score(int minScore, int increase) {
            this.minScore = minScore;
            this.increase = increase;
        }

        @Override
        public int compareTo(Score score) {
            return this.increase - score.increase;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int time = Integer.parseInt(st.nextToken()) * 24;
        int M = Integer.parseInt(st.nextToken());

        StringTokenizer scoreSt = new StringTokenizer(br.readLine());
        StringTokenizer increaseAmount = new StringTokenizer(br.readLine());

        Queue<Score> scoreQueue = new PriorityQueue<>(Collections.reverseOrder());
        int maxScore = 0;
        while (M-- > 0) {
            int score = Integer.parseInt(scoreSt.nextToken());
            maxScore += score;
            int increase = Integer.parseInt(increaseAmount.nextToken());
            scoreQueue.add(new Score(score, increase));
        }


        while (!scoreQueue.isEmpty() & time > 0) {
            Score sc = scoreQueue.poll();
            int temp = (100 - sc.minScore) / sc.increase;
            int remain = (100 - sc.minScore) % sc.increase;
            int increasedScore = Math.min(temp, time) * sc.increase;
            if (remain > 0) {
                scoreQueue.add(new Score(100 - remain, remain));
            }
            time -= temp;
            maxScore += increasedScore;
        }

        System.out.println(maxScore);
    }
}
