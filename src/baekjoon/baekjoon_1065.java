package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class baekjoon_1065 {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine())+1;
        int count=0;
        while(N-->1){
            int num=N;
            ArrayList <Integer>al=new ArrayList<Integer>();
            while(num>0){
                al.add(num%10);
                num=num/10;
            }
            if(al.size()==1 || al.size()==2) count++;
            else if(al.size()==4){}
            else if(al.get(0)+al.get(2)==2*al.get(1)) count++;
        }
        System.out.println(count);
    }
}