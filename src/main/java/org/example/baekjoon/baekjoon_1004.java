package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class baekjoon_1004 {

    public static int check_location(int [] location, int [] planetary){

        double start_distance=Math.sqrt(Math.pow(location[0]-planetary[0],2)+Math.pow(location[1]-planetary[1],2));
        double end_distance=Math.sqrt(Math.pow(location[2]-planetary[0],2)+Math.pow(location[3]-planetary[1],2));
    
        if (start_distance>planetary[2]&&end_distance<planetary[2]){return 1;}
        else if(start_distance<planetary[2]&&end_distance>planetary[2]){return 1;}
        else{return 0;}
    }    
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int test_case=Integer.parseInt(br.readLine());
        int [] location=new int[4];
        int test=0;
        while(test<test_case){
            StringTokenizer st=new StringTokenizer(br.readLine());
            for (int i=0;i<4;i++){
                location[i]=Integer.parseInt(st.nextToken());
            }
            int num=Integer.parseInt(br.readLine());
            int [][]planetary=new int[num][3];
            int result=0;
            for(int i=0;i<planetary.length;i++){
                st=new StringTokenizer(br.readLine());
                for(int j=0;j<3;j++){
                    planetary[i][j]=Integer.parseInt(st.nextToken());
                }
                result+=check_location(location, planetary[i]);
                st=null;
            }
            System.out.println(result);
            test++;
        }
        br.close();

    }
}
