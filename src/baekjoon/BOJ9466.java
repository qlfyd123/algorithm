package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/9466">BOJ 9466 텀 프로젝트</a>
 * */
public class BOJ9466 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int[] student = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                student[i] = Integer.parseInt(st.nextToken());
            }
            boolean[] visited = new boolean[n + 1];
            boolean[] team = new boolean[n + 1];
            int teamCount = n;
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    teamCount -= search(visited, team, student, i);
                }
                team[i] = true;
            }
            System.out.println(teamCount);
        }
    }


    /**
     * @param visited 방문 확인 배열
     * @param team    팀 확인 배열
     * @param student 원하는 학생 배열
     * @param s       현재 학생 번호
     * @return 팀을 이룬 학생의 수
     */
    private static int search(boolean[] visited, boolean[] team, int[] student, int s) {
        if (!visited[s]) {
            visited[s] = true;
            int teamCount = search(visited, team, student, student[s]);
            team[s] = true;
            return teamCount;
        } else {
            if (!team[s]) {
                return checkTeam(s, student, team, 0);
            } else {
                return 0;
            }
        }
    }

    /**
     * @param count 팀을 이룬 학생 수
     * @return count
     */
    private static int checkTeam(int s, int[] student, boolean[] team, int count) {
        team[s] = true;
        count++;
        if (!team[student[s]]) {
            return checkTeam(student[s], student, team, count);
        }
        return count;
    }
}
