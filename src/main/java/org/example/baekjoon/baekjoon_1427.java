package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class baekjoon_1427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] N = br.readLine().split("");
        ArrayList<Integer> al = new ArrayList<Integer>();
        for (String i : N) {
            al.add(Integer.parseInt(i));
        }

        Collections.sort(al, Collections.reverseOrder());
        for (int i : al) {
            System.out.print(i);
        }
    }
}