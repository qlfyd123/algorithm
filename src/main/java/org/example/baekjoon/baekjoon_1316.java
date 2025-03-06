package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;


// 그룹 단어란 단어에 존재하는 모든 문자에 대해서, 각 문자가 연속해서 나타나는 경우만을 말한다. 
// 예를 들면, ccazzzzbb는 c, a, z, b가 모두 연속해서 나타나고, 
// kin도 k, i, n이 연속해서 나타나기 때문에 그룹 단어이지만, 
// aabbbccb는 b가 떨어져서 나타나기 때문에 그룹 단어가 아니다.

// 단어 N개를 입력으로 받아 그룹 단어의 개수를 출력하는 프로그램을 작성하시오.

public class baekjoon_1316 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int count = 0;
        while (N-- > 0) {
            String word = br.readLine();
            if (is_groupword(word) == true)
                count++;
        }
        System.out.println(count);
    }
    //입력된 단어가 그룹단어인지 판별
    public static boolean is_groupword(String groupword) {

        char[] groupword_to_char = groupword.toCharArray();
        char temp = groupword_to_char[0];
        Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
        for (char i : groupword_to_char) {

            String i_toString = Character.toString(i);

            //같은 문자가 이미 존재하고 이전 문자와는 다른 경우
            if (ht.containsKey(i_toString) == true && i != temp)
                return false;
            //같은 문자가 이미 존재하고 이전 문자와 같은 경우
            else if (ht.containsKey(i_toString) == true && i == temp)
                ht.put(i_toString, ht.get(i_toString) + 1);
            //문자가 처음 들어온 경우
            else
                ht.put(i_toString, 1);
            temp = i;
        }
        return true;
    }
}
