import java.util.*;

public class q5_1{
    static public void main(String[] args){
        Scanner s=new Scanner(System.in);
        System.out.print("Enter the number of elements in first set:\n");
        int n1=s.nextInt();
        int[] s1=new int[11];
        System.out.print("Enter the elements of first set:\n");
        int element;
        for(int i=0;i<n1;i++){
            element=s.nextInt();
            if(s1[element]!=1){
                s1[element]=1;
            }
        }

        System.out.print("Enter the number of elements in second set:\n");
        int n2=s.nextInt();
        int[] s2=new int[11];
        System.out.print("Enter the elements of second set:\n");
        for(int i=0;i<n2;i++){
            element=s.nextInt();
            if(s2[element]!=1){
                s2[element]=1;
            }
        }
        s.close();
        long time_s=System.currentTimeMillis();
    
        intersection(s1, s2);
        union(s1, s2);
        complement(s1);
        complement(s2);

        long time_e = System.currentTimeMillis();
        System.out.println("Time taken: "+(time_e-time_s)+" milliseconds");
    }

    public static void intersection(int[] s1,int[] s2){
        int[] newset=new int[11];
        for(int i=0;i<11;i++){
            if(s1[i]==1 && s2[i]==1){
                newset[i]=1;
            }
            else{
                newset[i]=0;
            }
        }

        System.out.print("\nIntersection is:");
        for(int i=0;i<11;i++){
            if(newset[i]==1){
                System.out.print(" " + i);
            }
        }
    }

    public static void union(int[] s1,int[] s2){
        int[] newset=new int[11];
        for(int i=0;i<11;i++){
            if(s1[i]==1 || s2[i]==1){
                newset[i]=1;
            }
            else{
                newset[i]=0;
            }
        }

        System.out.print("\nUnion is:");
        for(int i=0;i<11;i++){
            if(newset[i]==1){
                System.out.print(" " + i);
            }
        }
    }

    public static void complement(int[] s1){
        int[] newset=new int[11];
        for(int i=0;i<11;i++){
            if(s1[i]==0){
                newset[i]=1;
            }
            else{
                newset[i]=0;
            }
        }

        System.out.print("\nComplement is:");
        for(int i=0;i<11;i++){
            if(newset[i]==1){
                System.out.print(" " + i);
            }
        }
    }

}