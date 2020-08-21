import java.util.regex.*;
import java.util.Scanner;
public class q2{
        static public void main(String[] arg){
            Scanner s=new Scanner(System.in);
            System.out.print("Enter the text:\n");
            String text=s.nextLine();
            System.out.print("Enter the words to be replaced separated by space:\n");
            String[] repl_vec=s.nextLine().split(" ");
            s.close();

            for(String x : repl_vec){
                Pattern p=Pattern.compile("\\b"+x+"\\b");
                Matcher m=p.matcher(text);
                if(m.find()){
                    String repl_str="";
                    for(int i=0;i<x.length();i++){
                        if(i==0){
                            repl_str+=x.charAt(0);
                        }
                        else{
                            repl_str+="*";
                        }
                    }
                    text=m.replaceAll(repl_str);
                }
            }

            System.out.print("New Paragraph is:\n"+ text);
        }
}