package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @see <a href="https://www.acmicpc.net/problem/status/14502">백준14502</a>
 */
class Virus {
    final private int[][] lab;
    private Stack<int[]> wall;
    final private int row, column;
    private int maxSafeZone = 0;
    private ArrayList<int[]> virusList;

    public Virus(int row, int column) {
        this.row = row;
        this.column = column;
        lab = new int[row][column];
    }


    public void getLabStructure(BufferedReader br) throws IOException {
        virusList = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < column; j++) {
                int virus = Integer.parseInt(st.nextToken());
                lab[i][j] = virus;
                if (virus == 2) {
                    virusList.add(new int[]{i, j});
                }
            }
        }
    }

    public int ans() {
        wall = new Stack<>();
        makeWall(0, -1);
        return maxSafeZone;
    }

    private void makeWall(int row, int column) {
        for (int i = row; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                if (i == row & j <= column) continue;
                if (lab[i][j] == 0) {
                    wall.add(new int[]{i, j});
                    if (wall.size() == 3) {
                        calcSafeZone();
                        wall.pop();
                        continue;
                    }
                    makeWall(i, j);
                    wall.pop();
                }
            }
        }
    }

    private void calcSafeZone() {
        int[][] labClone = new int[row][column];
        for (int i = 0; i < row; i++) {
            labClone[i] = lab[i].clone();
        }
        for (int[] wallLocation : wall) {
            labClone[wallLocation[0]][wallLocation[1]] = 1;
        }
        for (int[] virusLocation : virusList) {
            infection(virusLocation[0], virusLocation[1], labClone);
        }

        int safeZone = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (labClone[i][j] == 0) {
                    safeZone++;
                }
            }
        }
        maxSafeZone = Math.max(safeZone, maxSafeZone);
    }

    private void infection(int row, int column, int[][] labClone) {
        int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < 4; i++) {
            try {
                if ((labClone[row + direction[i][0]][column + direction[i][1]] == 0)) {
                    labClone[row + direction[i][0]][column + direction[i][1]] = 2;
                    infection(row + direction[i][0], column + direction[i][1], labClone);
                }
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
    }
}

public class baekjoon_14502 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int row = Integer.parseInt(st.nextToken());
        int column = Integer.parseInt(st.nextToken());

        Virus virus = new Virus(row, column);
        virus.getLabStructure(br);
        System.out.println(virus.ans());
    }
}
