package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <a href="https://www.acmicpc.net/problem/3190">백준 3190 뱀</a>
 */
public class BOJ3190 {
    static final int[][] direction = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        Set<String> apples = new HashSet<>();
        Queue<String[]> curve = new LinkedList<>();
        Deque<int[]> body = new ArrayDeque<>();
        body.add(new int[]{1, 1});
        int dir = 3;
        int playTime = 0;
        while (K-- > 0) {
            apples.add(br.readLine());
        }
        int L = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (L-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            curve.add(new String[]{st.nextToken(), st.nextToken()});
        }

        while (true) {
            playTime++;
            if (!moveForward(dir, apples, body, N)) {
                break;
            }
            dir = turn(dir, curve, playTime);
        }
        System.out.println(playTime);
    }

    /**
     * @param dir    움직일 방향
     * @param apples 사과 위치가 저장된 set
     * @param body   뱀의 몸
     * @param N      보드의 크기
     * @return 뱀이 앞으로 진행할수 있는지 여부
     */
    public static boolean moveForward(int dir, Set<String> apples, Deque<int[]> body, int N) {
        int[] head = new int[2];
        head[0] = body.peekFirst()[0] + direction[dir][0];
        head[1] = body.peekFirst()[1] + direction[dir][1];
        if (isCrushed(body, N, head)) {
            return false;
        }
        if (apples.contains(head[0] + " " + head[1])) {
            apples.remove(head[0] + " " + head[1]);
        } else {
            body.pollLast();
        }
        body.addFirst(head);
        return true;
    }

    /**
     * @param dir      진행방향
     * @param curve    뱀의 회전 순서
     * @param playTime 진행된 시간
     * @return 바뀐 진행방향
     */
    public static int turn(int dir, Queue<String[]> curve, int playTime) {
        if (!curve.isEmpty()) {
            if (Integer.parseInt(curve.peek()[0]) == playTime) {
                switch (curve.poll()[1]) {
                    case "D":
                        if (dir == 3) {
                            dir = 0;
                        } else {
                            dir++;
                        }
                        break;
                    case "L":
                        if (dir == 0) {
                            dir = 3;
                        } else {
                            dir--;
                        }
                        break;
                }
            }
        }
        return dir;
    }

    /**
     * @param head 뱀의 머리 위치
     * @return 뱀이 자기 자신의 몸통에 부딪히거나 벽에 부딪혀서 게임이 끝나는지여부를 반환
     */
    public static boolean isCrushed(Deque<int[]> body, int N, int[] head) {
        if (head[0] == N + 1 | head[1] == N + 1) {
            return true;
        } else if (head[0] == 0 | head[1] == 0) {
            return true;
        }
        Iterator<int[]> iterator = body.descendingIterator();
        for (int i = 0; i < body.size() - 1.; i++) {
            int[] bodyBlock = iterator.next();
            if (Arrays.equals(bodyBlock, head)) {
                return true;
            }
        }
        return false;
    }
}
