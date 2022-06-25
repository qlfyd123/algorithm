package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;


public class baekjoon_1159 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        HashMap  <Integer,Integer> map =new HashMap<Integer,Integer>();
        int num=Integer.parseInt(br.readLine());
        while(num-->0){
            String str=br.readLine();
            char [] ch=str.toCharArray();
            int first_char_to_int=(int) ch[0];
            if(map.get(first_char_to_int)==null) map.put(first_char_to_int,1);
            else map.put(first_char_to_int,map.get(first_char_to_int)+1);
        }
        String result="";
        Comparator<Entry<Integer, Integer>> comparator = new Comparator<Entry<Integer, Integer>>() {
            @Override
            public int compare(Entry<Integer, Integer> e1, Entry<Integer, Integer> e2) {
            return e1.getKey().compareTo(e2.getKey());
            }
        };
            
        for (int i=0;i<map.size();i++) {
            while(map.size()>1){
                Entry <Integer,Integer> max_entry= Collections.max(map.entrySet(),comparator);
                int max_key=max_entry.getKey();
                char key_char=(char) max_key;
                if(map.get(max_key)>=5) result=Character.toString(key_char)+result;
                map.remove(max_key);
            }
            for(Entry <Integer,Integer> entry:map.entrySet()){
                char key_char=(char) ((int)entry.getKey());
                if(entry.getValue()>=5) result=Character.toString(key_char)+result;
            }
        }

        if(result.equals("")) System.out.println("PREDAJA");
        else System.out.println(result);


        br.close();
    }
    
}
