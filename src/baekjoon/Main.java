package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    //폭탄 문자열을 순서대로 앞부터 탐색 연속될 필요는 x
    //폭탄 문자열이 완성되면 폭탄 문자열을 모두 지움
    //투포인터?

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] string = br.readLine().toCharArray();
        char[] bombString = br.readLine().toCharArray();
        int[] sameCharsIndex = new int[bombString.length];
        int ptrString = 0, ptrBombString = 0;
        while (ptrString < string.length) {
            if (string[ptrString] == bombString[ptrBombString]) {
                if (ptrBombString == bombString.length - 1) {
                    for (int i : sameCharsIndex) {
                        string[i] = 32;
                    }
                    ptrBombString = 0;
                } else {
                    sameCharsIndex[ptrBombString] = ptrString;
                    ptrBombString++;
                    ptrString++;
                }
            } else {
                ptrString++;
            }
        }
        String result = Arrays.toString(string).trim();
        System.out.println(result.equals("") ? "FRULA" : result);
    }
}
