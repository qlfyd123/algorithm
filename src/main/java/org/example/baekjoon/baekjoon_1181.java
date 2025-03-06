package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class baekjoon_1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Set<String> set = new HashSet<String>();
        ArrayList<String> result = new ArrayList<String>();

        while (N-- > 0) {
            set.add(br.readLine());
        }

        int length = 1;
        while (result.size() < set.size()) {
            ArrayList<String> al = sort(length, set);
            result.addAll(arrange(al));
            length++;
        }
        for (String i : result) {
            System.out.println(i);
        }
    }

    public static ArrayList<String> sort(int length, Set<String> wordSet) {

        ArrayList<String> al = new ArrayList<String>();
        for (String word : wordSet) {
            if (word.length() == length)
                al.add(word);
        }

        return al;
    }

    public static ArrayList<String> arrange(ArrayList<String> al) {
        Collections.sort(al);

        return al;
    }
}
