package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/16236">BOJ16236 아기상어</a>
 * */
public class BOJ16236 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] ocean = new int[N][N];
        Shark shark = null;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp == 9) {
                    shark = new Shark(i, j);
                } else {
                    ocean[i][j] = temp;
                }
            }
        }
        int loop = 0;
        while (true) {
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{shark.row, shark.column});
            int time = shark.findFish(ocean, new int[N][N], queue);
            shark.growth();
            if (time == 0) {
                System.out.println(loop);
                break;
            } else {
                loop += time;
            }
        }

    }

    static class Fish {
        int size, row, column;

        public Fish(int size, int i, int j) {
            this.size = size;

            row = i;
            column = j;
        }

        private Fish(int i, int j) {
            row = i;
            column = j;
        }
    }


    static class Shark extends Fish {
        int eatCount;

        public Shark(int i, int j) {
            super(i, j);
            size = 2;
            eatCount = 0;
        }

        public int findFish(int[][] oceans, int[][] distance, Queue<int[]> queue) {
            Fish nextFeed = null;
            while (!queue.isEmpty()) {
                int[] position = queue.poll();
                int row = position[0];
                int column = position[1];
                int oceanValue = oceans[row][column];
                if (canEat(oceans, row, column)) {
                    nextFeed = compareFeed(nextFeed, new Fish(oceanValue, row, column), distance);
                } else {
                    int[] dx = {-1, 0, 0, 1};
                    int[] dy = {0, -1, 1, 0};
                    for (int i = 0; i < 4; i++) {
                        int nextRow = row + dx[i];
                        int nextColumn = column + dy[i];
                        if (canMove(oceans, nextRow, nextColumn, distance)) {
                            if (distance[nextRow][nextColumn] == 0) {
                                distance[nextRow][nextColumn] = distance[row][column] + 1;
                                queue.add(new int[]{row + dx[i], column + dy[i]});
                            }
                        }
                    }
                }
            }
            if (nextFeed == null) {
                return 0;
            } else {
                eat(nextFeed, oceans);
                return distance[nextFeed.row][nextFeed.column];
            }
        }

        private boolean outOfBound(int row, int column, int length) {
            return row < 0 | row >= length | column < 0 | column >= length;
        }

        private Fish compareFeed(Fish nextFeed, Fish fish, int[][] distance) {
            if (nextFeed == null) return fish;
            if (distance[nextFeed.row][nextFeed.column] == distance[fish.row][fish.column]) {
                if (nextFeed.row == fish.row) {
                    return nextFeed.column > fish.column ? fish : nextFeed;
                }
                return nextFeed.row > fish.row ? fish : nextFeed;
            }
            return distance[nextFeed.row][nextFeed.column] > distance[fish.row][fish.column] ? fish : nextFeed;
        }

        private boolean canMove(int[][] oceans, int nextRow, int nextColumn, int[][] distance) {
            if (!outOfBound(nextRow, nextColumn, oceans.length)) {
                if (!(nextRow == row & nextColumn == column)) {
                    return oceans[nextRow][nextColumn] <= this.size;
                }
            }
            return false;
        }

        private boolean canEat(int[][] oceans, int nextRow, int nextColumn) {
            if (oceans[nextRow][nextColumn] == 0) return false;
            return oceans[nextRow][nextColumn] < this.size;
        }

        private void eat(Fish feed, int[][] oceans) throws NullPointerException {
            oceans[feed.row][feed.column] = 0;
            this.eatCount++;
            this.row = feed.row;
            this.column = feed.column;
        }

        public void growth() {
            if (this.size == this.eatCount) {
                this.size++;
                this.eatCount = 0;
            }
        }
    }
}
