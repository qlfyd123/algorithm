package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// TODO: 2023-01-08 벽세우는 함수 마무리,lab배열 클론처리
class Virus {
    final private int[][] lab;
    private int[][] labClone;
    private Stack<int[]> wall;
    final private int row, column;
    private int maxSafeZone = 0;

    public Virus(int row, int column) {
        this.row = row;
        this.column = column;
        lab = new int[row][column];
    }


    public void getLabStructure(BufferedReader br) throws IOException {
        for (int i = 0; i < row; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < column; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public int ans() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (lab[i][j] == 0) {
                    labClone = lab.clone();
                    makeWall(i, j);
                }
            }
        }
    }

    private int[][] makeWall(int row, int column) {
        labClone[row][column] = 1;
        if (wall.size() == 2) {
            calcSafeZone(labClone);
        } else {
            wall.add(new int[]{row, column});
        }
        for (int i = row; i < this.row; ) {
            for (int j = column + 1; j < this.column; j++) {
                if (labClone[row][column] == 0) {
                    if ()
                        makeWall(i, j, labClone);
                    return lab;
                }
            }
            i++;
        }
        return lab;
    }

    private void calcSafeZone(int[][] labClone) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (labClone[i][j] == 2) {
                    infection(row, column);
                }
            }
        }

        int safeZone = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (lab[i][j] == 0) {
                    safeZone++;
                }
            }
        }
        maxSafeZone = Math.max(safeZone, maxSafeZone);
    }

    private void infection(int row, int column) {
        int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] labClone = lab.clone();
        for (int i = 0; i < 4; i++) {
            try {
                if ((labClone[row + direction[i][0]][column + direction[i][1]] == 0)) {
                    labClone[row + direction[i][0]][column + direction[i][1]] = 2;
                    infection(row + direction[i][0], column + direction[i][1]);
                }
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int row = Integer.parseInt(st.nextToken());
        int column = Integer.parseInt(st.nextToken());

        Virus virus = new Virus(row, column);
        virus.getLabStructure(br);
    }
}
