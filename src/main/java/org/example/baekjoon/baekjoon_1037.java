package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_1037 {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());

        int max=0;
        int min=0;

        StringTokenizer st=new StringTokenizer(br.readLine());

        while(N-->0){
            int temp=Integer.parseInt(st.nextToken());
            max=Math.max(max, temp);
            min=Math.min(min, temp);
            if(min==0) min=temp;
        }

        if(N==1) min=2;
        System.out.println(max*min);
    }
}
