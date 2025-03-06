package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class baekjoon_10610 {
    public static void main(String[] args) throws IOException, NumberFormatException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();
        int sum = 0;

        List<Integer> al = new ArrayList<Integer>();

        for (String str : N.split("")) {
            int num = Integer.parseInt(str);
            al.add(num);
            sum += num;
        }
        if (sum % 3 == 0) {

            Collections.sort(al, Collections.reverseOrder());

            int indexOfZero = al.indexOf(0);

            if (indexOfZero == -1) {

                System.out.println(-1);
                return;

            } else {

                for (int i : al) {

                    System.out.print(i);
                }
            }

        } else {

            System.out.println(-1);
        }
    }
}
