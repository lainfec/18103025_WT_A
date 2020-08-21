import java.util.*;
public class q1{  
	    static public void main(String[] args) { 
	        Scanner s=new Scanner(System.in);
	        System.out.print("Enter the Text:\n");
	        String txt=s.next();
	        System.out.print("Enter the Pattern:\n");
            String pat=s.next();
            s.close();

            int n=txt.length();
            int k=pat.length();
            int ans=0;
            if(n>=k){
                HashMap<Character, Integer> pat_map=new HashMap<Character,Integer>();
                for(int i=0;i<k;i++){
                    pat_map.put(pat.charAt(i),1+pat_map.getOrDefault(pat.charAt(i), 0));
                }

                HashMap<Character, Integer> str_map=new HashMap<Character,Integer>();
                int j=0;
                for(int i=0;i<n;i++){
                    if(i>=k){
                        str_map.put(txt.charAt(j),str_map.get(txt.charAt(j))-1);
                        j++;
                    }
                    str_map.put(txt.charAt(i),1+str_map.getOrDefault(txt.charAt(i), 0));
                    
                    if(str_map.equals(pat_map)){
                        ans++;
                    }
                }
            }

            System.out.print("The number of times the substring appears is "+ ans);
	    } 
} 