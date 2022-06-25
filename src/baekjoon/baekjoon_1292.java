package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_1292 {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int A=Integer.parseInt(st.nextToken());
        int B=Integer.parseInt(st.nextToken());
        int index=0;
        int sum_A=0;
        int sum_B=0;
        int i=0;
        while(index<A){
            i++;
            sum_A+=i*i;
            index+=i;
        }
        sum_A=sum_A-(index-A+1)*i;
        i=0;
        index=0;
        while(index<B){
            i++;
            sum_B+=i*i;
            index+=i;

        }
        sum_B-=(index-B)*i;


        System.out.println(sum_B-sum_A);
    }
}
