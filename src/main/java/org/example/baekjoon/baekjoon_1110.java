package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class baekjoon_1110 {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        if(N<10) N=N*10;
        int result=N;
        int count=0;
        int remainder,quotient;
        do {
            remainder =result%10;
            quotient =result/10;
            result=(remainder*10)+(remainder+quotient)%10;
            count++;
        }while(result!=N);
        System.out.println(count);
    }
}
