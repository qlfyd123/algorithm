package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/**
 * <a href="https://www.acmicpc.net/problem/16235">BOJ16235 나무 재테크</a>
 * */
public class BOJ16235 {

    private static class Ground {
        Queue<Integer> trees;
        Queue<Integer> deadTrees;
        int breededTreeCount;
        int nutrition;

        public Ground() {
            trees = new PriorityQueue<>();
            deadTrees = new LinkedList<>();
            nutrition = 5;
            breededTreeCount = 0;
        }

        private void resetNutrition(int tree, Queue<Integer> afterSpring) {
            int remainNutrition = nutrition - tree;
            if (remainNutrition < 0) {
                deadTrees.add(tree);
            } else {
                afterSpring.add(tree + 1);
                nutrition = remainNutrition;
            }
        }

        public void spring() {
            Queue<Integer> afterSpring = new PriorityQueue<>();
            while (!trees.isEmpty()) {
                resetNutrition(trees.poll(), afterSpring);
            }
            trees = afterSpring;
        }

        public void summer() {
            while (!deadTrees.isEmpty()) {
                nutrition += deadTrees.poll() >> 1;
            }
        }

        public void fall(List<Ground> nearGround) {
            for (int tree : trees) {
                if (tree % 5 == 0) {
                    for (Ground ground : nearGround) {
                        ground.breededTreeCount++;
                    }
                }
            }
        }

        private void addBreededTree() {
            for (int i = 1; i <= breededTreeCount; i++) {
                trees.add(1);
            }
            breededTreeCount = 0;
        }

        public void winter(int i) {
            addBreededTree();
            nutrition += i;
        }

    }

    public static void spring(Ground[][] field) {
        for (Ground[] grounds : field) {
            for (Ground ground : grounds) {
                ground.spring();
            }
        }
    }

    public static void summer(Ground[][] field) {
        for (Ground[] grounds : field) {
            for (Ground ground : grounds) {
                ground.summer();
            }
        }
    }

    public static void fall(Ground[][] field) {
        for (int i = 0; i < field.length; i++) {
            Ground[] grounds = field[i];
            for (int j = 0; j < grounds.length; j++) {
                Ground ground = grounds[j];
                List<Ground> nearGround = getNearGround(i, j, field);
                ground.fall(nearGround);
            }
        }
    }

    public static void winter(Ground[][] field, int[][] nutrition) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                Ground ground = field[i][j];
                ground.winter(nutrition[i][j]);
            }
        }
    }

    private static List<Ground> getNearGround(int i, int j, Ground[][] field) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        List<Ground> nearGround = new ArrayList<>();
        for (int k = 0; k < 8; k++) {
            int x = dx[k] + i;
            int y = dy[k] + j;
            if (inBound(x, y, field.length)) {
                nearGround.add(field[x][y]);
            }
        }
        return nearGround;
    }

    private static boolean inBound(int x, int y, int n) {
        if (x >= n | x < 0) {
            return false;
        }
        return (y >= 0 & y < n);
    }

    private static void year(Ground[][] field, int[][] nutrition) {
        spring(field);
        summer(field);
        fall(field);
        winter(field, nutrition);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] initialNutrition = new int[N][N];
        Ground[][] field = new Ground[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                field[i][j] = new Ground();
                initialNutrition[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            field[x][y].trees.add(age);
        }

        while (K-- > 0) {
            year(field, initialNutrition);
        }
        int count = 0;
        for (Ground[] grounds : field) {
            for (Ground ground : grounds) {
                count += ground.trees.size();
            }
        }
        System.out.println(count);
    }

}
