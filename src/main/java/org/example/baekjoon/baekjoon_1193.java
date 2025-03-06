package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_1193 {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine());
        int sequence=0;
        int total=0;
        while(total<N){
            sequence++;
            total=(sequence*(sequence+1))/2;
        }
        int a=1+total-N;
        int b=sequence-total+N;
        if(sequence%2==0) {
            System.out.println(b+"/"+a);
        }
        else if(sequence%2!=0){
            System.out.println(a+"/"+b);
        }
    }
}
