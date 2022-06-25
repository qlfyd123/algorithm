package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_1233 {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int a=Integer.parseInt(st.nextToken());
        int b=Integer.parseInt(st.nextToken());
        int c=Integer.parseInt(st.nextToken());
        int sum[]=new int [a+b+c+1];
        int max=0;
        int index=0;
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++){
                for(int k=0;k<c;k++){
                    sum[i+j+k+3]+=1;
                }
            }   
        }
        for(int i=0;i<sum.length;i++){
            if(sum[i]>max) {
                max=sum[i];
                index=i;
            }
        }

        System.out.println(index);
        
    }
}
