import java.util.*;
public class q2{
    static public void main(String[] args){
        Scanner s=new Scanner(System.in);
        System.out.print("Enter size of the array:\n");
        int n=s.nextInt();
        int [] arr=new int[n];
        HashMap<Integer,Integer> map1=new HashMap<Integer,Integer>();
        System.out.print("Enter the elements(should be in range of 0-20 inclusive):\n");
        for(int i=0;i<n;i++){
            arr[i]=s.nextInt();
            if(arr[i]>20 || arr[i]<0){
                System.out.print("Invalid element detected!\n");
                return;
            }
            map1.put(arr[i],1+map1.getOrDefault(arr[i], 0));
        }
        s.close();

        int indx=0;
        for(int i=0;i<21;i++){
            for(int j=0;j<map1.getOrDefault(i,0);j++){
                arr[indx++]=i;
            }
        }
        for(int i=0;i<n;i++){
            System.out.print(arr[i]+ " ");
        }
    }
}