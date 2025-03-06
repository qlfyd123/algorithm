package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//lj e s= nj a k
public class baekjoon_2941 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] alphabet = br.readLine().split("");
        int count = 0;

        for (int i = 0; i < alphabet.length; i++) {
            String flag = alphabet[i];
            String temp = "";
            if (i != alphabet.length - 1)
                temp = alphabet[i + 1];
            switch (flag + temp) {
                case "c=":
                    count++;
                    i++;
                    break;
                case "c-":
                    count++;
                    i++;
                    break;
                case "dz":
                    if (i != alphabet.length - 2 && alphabet[i + 2].equals("=")) {
                        count++;
                        i += 2;
                        break;
                    }
                    count++;
                    break;
                case "d-":
                    count++;
                    i++;
                    break;
                case "lj":
                    count++;
                    i++;
                    break;
                case "nj":
                    count++;
                    i++;
                    break;
                case "s=":
                    count++;
                    i++;
                    break;
                case "z=":
                    count++;
                    i++;
                    break;
                default:
                    count++;
                    break;
            }

        }

        System.out.println(count);
    }

}
