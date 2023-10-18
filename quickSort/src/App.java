import java.util.Random;
import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int[] num=new int[n];
        for (int i = 0; i < num.length; i++) {
            num[i]=input.nextInt();
        }
        int []temp=new int[n];
        int left=0; int right=n-1;
        quickSort(num,temp,left,right);
        for (int i = 0; i < temp.length; i++) {
            System.out.print(num[i]+" ");
        }
            
        
    }

    public static void quickSort(int[]array1,int[] temp,int left,int right){
        if(left>=right){return;}
        Random r=new Random();
        int pivot=r.nextInt(right-left+1)+left;
        int k=0,j=0;
        for (int i = left; i < right+1; i++) {
            if(i!=pivot){
                if(array1[i]<array1[pivot]){
                    temp[left+k]=array1[i];k++;
                }else{
                    temp[right-j]=array1[i];j++;
                }
                  
            }
        }
        temp[left+k]=array1[pivot];
        for (int i = 0; i < temp.length; i++) {
            array1[i]=temp[i];
        }
        quickSort(array1, temp, left, left+k);
        quickSort(array1,temp,left+k+1,right);
    }
}
