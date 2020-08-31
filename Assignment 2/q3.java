import java.util.*;
public class q3{
    static public void main(String[] args){
        Scanner s=new Scanner(System.in);
        System.out.print("Enter the string(lowercase only):\n");
        String s1=s.next();
        s.close();
        HashMap<Character,Integer> map1=new HashMap<Character,Integer>();
        int n=s1.length();
        for(int i=0;i<n;i++){
            map1.put(s1.charAt(i),1+map1.getOrDefault(s1.charAt(i), 0));
        }

        String ans="";
        for(char c='a';c<='z';c++){
            for(int i=0;i<map1.getOrDefault(c, 0);i++){
                ans+=c;
            }
        }
        System.out.print(ans);


    }
}