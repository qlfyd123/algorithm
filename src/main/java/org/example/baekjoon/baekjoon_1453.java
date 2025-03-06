package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class baekjoon_1453 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] seat = br.readLine().split(" ");
        ArrayList<String> al = new ArrayList<String>();
        int count = 0;
        for (String i : seat) {
            if (al.contains(i))
                count++;
            else
                al.add(i);
        }

        System.out.println(count);
    }
}
