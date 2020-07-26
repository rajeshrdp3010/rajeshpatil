import java.util.Arrays;
import java.util.Scanner;

public class StringCommonSubstring {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        z:
        while(n -- > 0 ){
            char [] a = in.next().toCharArray();
            char [] b = in.next().toCharArray();
            
            Arrays.sort(b);
            for(char c : a){
                if( Arrays.binarySearch(b , c) >= 0){
                    
                    System.out.println("YES");
                    continue z;
                }
            }
            System.out.println("NO");
            
        }
    }
}