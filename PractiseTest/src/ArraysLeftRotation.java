import java.util.Scanner;
/**
 * When we perform d= 4 left rotations, the array undergoes the following sequence of changes:
   [1 , 2,  3 , 4 , 5] -> [2 , 3 , 4,  5 , 1] -> [3 , 4 , 5 , 1 , 2] -> [4 , 5 , 1 , 2 , 3] -> [5 , 1 , 2 , 3 , 4]

 * @author rpatil
 *
 */
public class ArraysLeftRotation {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        reverse(a, 0, n);
        reverse(a, 0, n-k);
        reverse(a, n-k, n);
        for(int i=0; i<n; i++){
           System.out.print(a[i]+" ");
        }
    }
    
    private static void reverse(int[] a, int j, int k){
        int l = j+k;
        for(int i=j; i<l/2; i++){
            int temp = a[i];
            a[i] = a[l-1-i];
            a[l-1-i] = temp;
        }
    }
}
