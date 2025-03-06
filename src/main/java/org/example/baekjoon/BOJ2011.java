package org.example.baekjoon;

import java.io.*;
/**
 * <a href="https://www.acmicpc.net/problem/2011">BOJ 2011 암호코드</a>
 * */
public class BOJ2011 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] password = br.readLine().chars().map(o -> o - 48).toArray();
        br.close();
        int[] count = new int[password.length + 1];
        for (int i = 0; i < password.length; i++) {
            if (i == 0) {
                if (password[i] != 0) {
                    count[0] = 1;
                    count[1] = 1;
                } else {
                    break;
                }
            } else {
                if (password[i] == 0) {
                    if (password[i - 1] == 0) {
                        break;
                    } else {
                        int linkedChar = 10 * password[i - 1] + password[i];
                        if (linkedChar < 27) {
                            count[i + 1] = count[i - 1];
                        } else {
                            break;
                        }
                    }
                } else {
                    int index = i + 1;
                    if (password[i - 1] != 0) {
                        int linkedChar = 10 * password[i - 1] + password[i];
                        if (linkedChar < 27) {
                            count[index] = count[index - 1] + count[index - 2];
                        } else {
                            count[index] = count[index - 1];
                        }
                    } else {
                        count[index] = count[index - 1];
                    }

                }
                if (count[i + 1] > 1000000) {
                    count[i + 1] -= 1000000;
                }
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(count[count.length - 1]));
        bw.close();
    }
}
