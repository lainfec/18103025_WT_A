import java.util.*;

public class q4{
        static public void main(String [] args){
            Scanner s=new Scanner(System.in);
            System.out.print("Enter first string/number:\n");
            String s1=s.next();
            System.out.print("Enter second string/number:\n");
            String s2=s.next();
            s.close();

            HashMap<Character, Integer> s1_map=new HashMap<Character,Integer>();
             for(int i=0;i<s1.length();i++){
                s1_map.put(s1.charAt(i),1+s1_map.getOrDefault(s1.charAt(i), 0));
            }

            HashMap<Character, Integer> s2_map=new HashMap<Character,Integer>();
             for(int i=0;i<s2.length();i++){
                s2_map.put(s2.charAt(i),1+s2_map.getOrDefault(s2.charAt(i), 0));
            }

            String ans="not ";
            if(s1_map.equals(s2_map)){
                ans="";
            }

            System.out.print("The two input strings are "+ans+"anagrams.");
        }
}