package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class virus {
    final private int[][] lab;
    private ArrayList<int[]> wall;
    final private int row, column;
    private int maxSafeZone = 0;

    public virus(int row, int column) {
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

    public void makeWall(int row, int column) {
        wall.add(new int[]{row, column});
        lab[row][column] = 1;
        if (wall.size() == 3) {
            calcSafeZone();
        } else {
            for (int i = row; i < this.row; ) {
                for (int j = column + 1; j < this.column; j++) {
                    if (lab[row][column] == 0) {
                        makeWall(i, j);
                        return;
                    }
                }
                i++;
            }
        }
    }

    public int calcSafeZone() {
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if()
            }
        }
    }

    private void infection(){

    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int row = Integer.parseInt(st.nextToken());
        int column = Integer.parseInt(st.nextToken());

    }
}
