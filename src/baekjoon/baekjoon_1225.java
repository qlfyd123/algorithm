package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_1225 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        String A=st.nextToken();
        String B=st.nextToken();
        char[] A_char_array=A.toCharArray();
        char[] B_char_array=B.toCharArray();
        long sum=0;
        for(char x:A_char_array){
            int i=Character.getNumericValue(x);
            for(char y:B_char_array){
                int j=Character.getNumericValue(y);
                int multi=i*j;
                sum=sum+multi;
            }
        }
        System.out.println(sum);       
    }
}
