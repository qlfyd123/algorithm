package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 어떤 수 X가 주어졌을 때, X의 모든 자리수가 역순이 된 수를 얻을 수 있다. Rev(X)를 X의 모든 자리수를 역순으로 만드는 함수라고 하자.
//  예를 들어, X=123일 때, Rev(X) = 321이다. 그리고, X=100일 때, Rev(X) = 1이다.

// 두 양의 정수 X와 Y가 주어졌을 때, Rev(Rev(X) + Rev(Y))를 구하는 프로그램을 작성하시오
public class baekjoon_1357 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        X = rev(X);
        Y = rev(Y);

        System.out.println(rev(X + Y));
    }

    // 입력된 숫자를 역순으로 정렬
    private static int rev(int number) {

        String[] number_to_String_array = String.valueOf(number).split("");
        int rev_number = 0;
        int temp = 1;
        for (String i : number_to_String_array) {
            rev_number = rev_number + Integer.parseInt(i) * temp;
            temp = temp * 10;
        }
        return rev_number;
    }
}
