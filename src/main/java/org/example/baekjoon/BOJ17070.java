package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/17070">백준 17070번: 파이프 옮기기 1</a>
 */
public class BOJ17070 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = dfs(new Pipe(), map);
        System.out.println(result);
    }

    private static int dfs(Pipe pipe, int[][] map) {
        if (pipe.head[0] == map.length - 1 && pipe.head[1] == map.length - 1) {
            return 1;
        }
        int count = 0;
        Pipe[] nextPipes = pipe.nextPipe();

        for (Pipe nextPipe : nextPipes) {
            if (isValid(nextPipe, map)) {
                count += dfs(nextPipe, map);
            }
        }

        return count;
    }

    private static boolean isValid(Pipe pipe, int[][] map) {
        return switch (pipe.direction) {
            case HORIZONTAL, VERTICAL -> {
                if (pipe.head[0] < map.length && pipe.head[1] < map.length) {
                    yield map[pipe.head[0]][pipe.head[1]] == 0;
                } else {
                    yield false;
                }
            }
            case DIAGONAL -> {
                if (pipe.head[0] < map.length && pipe.head[1] < map.length) {
                    yield map[pipe.head[0]][pipe.head[1]] == 0 &&
                        map[pipe.head[0] - 1][pipe.head[1]] == 0 &&
                        map[pipe.head[0]][pipe.head[1] - 1] == 0;
                } else {
                    yield false;
                }
            }
        };
    }

    private static class Pipe {

        int[] head;
        int[] tail;
        Direction direction;

        public Pipe(int[] head, int[] tail) {
            this.head = head;
            this.tail = tail;

            if (head[0] == tail[0]) {
                this.direction = Direction.HORIZONTAL;
            } else if (head[1] == tail[1]) {
                this.direction = Direction.VERTICAL;
            } else {
                this.direction = Direction.DIAGONAL;
            }
        }

        public Pipe() {
            this.head = new int[]{0, 1};
            this.tail = new int[]{0, 0};
            this.direction = Direction.HORIZONTAL;
        }

        public Pipe[] nextPipe() {
            return switch (direction) {
                case HORIZONTAL -> new Pipe[]{
                    new Pipe(new int[]{head[0], head[1] + 1}, this.head),
                    new Pipe(new int[]{head[0] + 1, head[1] + 1}, this.head),
                };
                case VERTICAL -> new Pipe[]{
                    new Pipe(new int[]{head[0] + 1, head[1]}, this.head),
                    new Pipe(new int[]{head[0] + 1, head[1] + 1}, this.head),
                };
                case DIAGONAL -> new Pipe[]{
                    new Pipe(new int[]{head[0], head[1] + 1}, this.head),
                    new Pipe(new int[]{head[0] + 1, head[1]}, this.head),
                    new Pipe(new int[]{head[0] + 1, head[1] + 1}, this.head),
                };
            };
        }

        private enum Direction {
            HORIZONTAL, VERTICAL, DIAGONAL
        }

    }
}
