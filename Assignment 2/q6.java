import java.io.*;
public class q6{
    static public void main(String[] args){
        Console con=System.console();
        if(con==null)  
        { 
            System.out.print("No console available"); 
            return; 
        } 
        String str=con.readLine("Enter a number: ");
        int n=Integer.parseInt(str);
        con.printf("%d ",n);
        while(n!=1){
            if(n%2==0){
                n=n/2;
                con.printf(" %d ",n);
            }
            else{
                n=3*n+1;
                con.printf(" %d ",n);
            }

        }
        
    }
}