public class q4{
    static public void main(String[] args){
        long sum=0,i=1;
        System.out.print("The numbers for which summation is equal to their square are:\n");
        for(;i<Integer.MAX_VALUE;i++){
            sum+=i;
            if(sum==i*i){
                System.out.print(i + "\n");
            }
            if(sum>Integer.MAX_VALUE){
                break;
            }
        }

        System.out.print("The maximum value of i was: " + i);
    }
}