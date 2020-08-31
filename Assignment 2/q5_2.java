import java.util.*;

public class q5_2{
    static public void main(String[] args){
        Scanner s=new Scanner(System.in);
        Set<Integer> s1=new HashSet<Integer>();
        Set<Integer> s2=new HashSet<Integer>();
        Set<Integer> universal=new HashSet<Integer>();
        universal.addAll(Arrays.asList(new Integer[]{0,1,2,3,4,5,6,7,8,9,10}));
        
        System.out.print("Enter the number of elements in first set:\n");
        int n1=s.nextInt();
        System.out.print("Enter the elements of first set:\n");
        int element;
        for(int i=0;i<n1;i++){
            element=s.nextInt();
            s1.add(element);
        }

        System.out.print("Enter the number of elements in second set:\n");
        int n2=s.nextInt();
        System.out.print("Enter the elements of second set:\n");
        for(int i=0;i<n2;i++){
            element=s.nextInt();
            s2.add(element);
        }
        s.close();

        long time_s=System.currentTimeMillis();
        
        Set<Integer> inters=new HashSet<Integer>(s1);
        inters.retainAll(s2);
        System.out.println("Intersection of the sets : "+inters);

        Set<Integer> union=new HashSet<Integer>(s1);
        union.addAll(s2);
        System.out.println("Union of the sets : "+union);

        Set<Integer> comps1= new HashSet<Integer>(universal);
        comps1.removeAll(s1);
        System.out.println("Complement of set 1: "+comps1);

        Set<Integer> comps2= new HashSet<Integer>(universal);
        comps2.removeAll(s2);
        System.out.println("Complement of set 2: "+comps2);

        long time_e = System.currentTimeMillis();
        System.out.println("Time taken: "+(time_e-time_s)+" milliseconds");
 
    }

}