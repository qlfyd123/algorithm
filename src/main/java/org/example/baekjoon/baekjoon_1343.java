package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_1343 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(Polyomino(br.readLine()));
    }

    public static String replace(String polyomino, int count, int end) {

        if (count == 0) {
            return polyomino;
        }
        char[] arr = polyomino.toCharArray();
        int index = end - count;

        while (index != end) {

            if (count % 2 == 0) {

                if (count >= 4) {

                    for (int i = index; i < index + 4; i++) {

                        arr[i] = 'A';
                    }
                    index += 4;
                    count -= 4;

                } else {

                    for (int i = index; i < index + 2; i++) {

                        arr[i] = 'B';
                    }
                    index += 2;
                    count -= 2;
                }
            } else {
                return "-1";
            }
        }
        // String result = arr.
        return String.valueOf(arr);
    }

    public static String Polyomino(String polyomino) {

        int count = 0;

        for (int i = 0; i < polyomino.length(); i++) {

            char letter = polyomino.charAt(i);
            if (letter == 'X') {
                count++;
            } else {
                polyomino = replace(polyomino, count, i);
                count = 0;
                if (polyomino.equals("impossible"))
                    return "-1";
            }
        }

        polyomino = replace(polyomino, count, polyomino.length());
        return polyomino;
    }
}
