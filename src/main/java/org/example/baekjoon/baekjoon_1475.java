package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class baekjoon_1475 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Integer> number = new ArrayList<Integer>();

        for (String i : br.readLine().split("")) {
            number.add(Integer.parseInt(i));
        }

        ArrayList<Integer> al = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        for (int i : number) {
            al.set(i, al.get(i) + 1);
        }
        int other = al.get(6) + al.get(9);
        int max = (int) (Math.round(other / 2.0));
        al.remove(9);
        al.remove(6);

        Collections.sort(al, Collections.reverseOrder());
        other = al.get(0);

        if (max < other)
            max = other;

        System.out.println(max);
    }
}
