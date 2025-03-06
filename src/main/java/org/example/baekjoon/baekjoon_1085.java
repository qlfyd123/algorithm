package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_1085 {
    public static void main(String[] args) throws Exception{
        int x,y,w,h;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        x=Integer.parseInt(st.nextToken());
        y=Integer.parseInt(st.nextToken());
        w=Integer.parseInt(st.nextToken());
        h=Integer.parseInt(st.nextToken());
        System.out.println(min(x, y, w, h));
    }
    public static int min(int x,int y, int w, int h){
        int min_1,min_2;
        if(x>(w/2)) min_1=w-x;
        else min_1=x;

        if(y>(h/2))min_2=h-y;
        else min_2=y;

        return Math.min(min_1,min_2);
    }
}
