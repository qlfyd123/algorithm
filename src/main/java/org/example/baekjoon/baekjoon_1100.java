package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjoon_1100 {
    public static void main(String[] args) throws Exception{
        int y=-1;

        int count=0;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        while(y++<7){
            StringTokenizer st=new StringTokenizer(br.readLine(),".|F",true);
            int x=-1;
            while(x++<7){
                String token=st.nextToken();
                if(token.equals("F") && (x+y)%2==0) count++;
                else if(token.equals("FFFFFFFF")) {count=count+4; break;}
            }
        }
        br.close();
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(count+"\n");
        bw.flush();
        bw.close();
        // System.out.println(count);
    }
}
