import java.util.*;
public class q1{
    static public void main(final String [] args){
        Scanner s=new Scanner(System.in);
        System.out.print("Enter the first string:\n");
        String s1=s.next();
        System.out.print("Enter the second string:\n");
        String s2=s.next();
        s.close();
        int i=0;
		boolean flag=false;
        int n1=s1.length(),n2=s2.length();
        while(i<n1 & i<n2){
            if(s1.charAt(i)<s2.charAt(i)){
                System.out.print(s1+" is smaller than "+s2);
                flag=true; break;
            }
            else if(s1.charAt(i)>s2.charAt(i)){
                System.out.print(s2+" is smaller than "+s1);
                flag=true; break;
            }
            else{
                i++;
            }
        }
        if(!flag){
            if(n1<n2){
                System.out.print(s1+" is smaller than "+s2);
            }
            else if(n1>n2){
                System.out.print(s2+" is smaller than "+s1);
            }
            else{
                System.out.print("Both strings are the same.");
            }
        }
    }
}