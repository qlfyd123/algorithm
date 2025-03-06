package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_2195 {
    /*
    * https://www.acmicpc.net/problem/2195
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] S = br.readLine().toCharArray();
        char[] P = br.readLine().toCharArray();

        int count = 0;

        for (int i = 0; i < P.length; ) {
            int index = 0;
            int maxIndex = 0;
            for (int j = 0; j < S.length; j++) {
                char c = S[j];
                try {
                    char p = P[i + index];
                    if (p == c) {
                        index++;
                    } else {
                        /*
                        * aassdsdsff
                        * asdafasdsafas
                        * 다음과 같은 경우 i=0에서는 S 와 P의 값이 모두 같지만
                        * i=1에서는 서로 다른데 이때에도 S배열의 인덱스가 증가하면 S배열은 i=2일때부터 비교하게 되지만
                        * P배열은 인덱스가 0으로 초기화되므로 as의 값이 같은것을 구별하지 못하고 지나가게 된다
                        * 따라서 이전에 S와 P의 값이 같은경우가 존재했다면 S배열의 인덱스를 1감소시켜준다.
                        * */
                        if (index > 0) {
                            j--;
                        }
                        index = 0;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
                maxIndex = Math.max(maxIndex, index);

            }
            i += maxIndex;
            count++;
        }
        System.out.println(count);
    }
}
