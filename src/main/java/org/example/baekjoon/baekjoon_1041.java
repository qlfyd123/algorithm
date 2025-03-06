package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon_1041 {
    private static int min(int index, int[] dice) {
        int min = 0;
        int a = Math.min(dice[0], dice[5]);
        int b = Math.min(dice[1], dice[4]);
        int c = Math.min(dice[2], dice[3]);
        switch (index) {
            case 1 -> min = Math.min(a, Math.min(b, c));
            case 2 -> min = Math.min(a + b, Math.min(b + c, a + c));
            case 3 -> min = a + b + c;
            case 4 -> {
                Arrays.sort(dice);
                for (int i = 0; i < 5; i++) {
                    min += dice[i];
                }
            }
        }

        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dice = new int[6];

        StringTokenizer st = new StringTokenizer(br.readLine());



        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        if(N==1){
            System.out.println(min(4,dice));

            return;
        }


        int vertexSum = min(3, dice);
        int edgeSum = min(2, dice);
        int planeSum = min(1, dice);


        BigInteger NewN = new BigInteger(String.valueOf(N - 2));

        BigInteger vertex = new BigInteger(String.valueOf(4));

        BigInteger edge = NewN.multiply(BigInteger.valueOf(8));
        edge = edge.add(BigInteger.valueOf(4));

        BigInteger plane = NewN.multiply(NewN).multiply(BigInteger.valueOf(5)).add(NewN.multiply(BigInteger.valueOf(4)));
        vertex = vertex.multiply(BigInteger.valueOf(vertexSum));
        edge = edge.multiply(BigInteger.valueOf(edgeSum));
        plane = plane.multiply(BigInteger.valueOf(planeSum));

        System.out.println(vertex.add(edge.add(plane)));
    }
}