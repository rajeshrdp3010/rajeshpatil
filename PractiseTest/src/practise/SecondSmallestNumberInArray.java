package practise;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SecondSmallestNumberInArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[]={1,2,5,6,3,2};  
		int b[]={44,66,99,77,33,22,55};  
		System.out.println("Second smallest: "+findSecondSmallestNumber(a));  
		System.out.println("Second smallest: "+findSecondSmallestNumberUsingArraysSort(b));
		
		Integer c[]={5,6,12,1,2,77,43};  
		System.out.println("Second Smallest: "+findSecondSmallestNumberUsingCollectionsSort(c));  

	}

	private static int findSecondSmallestNumber(int[] array) {
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array.length - 1 - i; j++){
                if(array[j] > array[j + 1]){
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array[1];
	}

	private static int findSecondSmallestNumberUsingArraysSort(int[] array) {
		Arrays.sort(array);
		return array[1];
	}
	
	private static int findSecondSmallestNumberUsingCollectionsSort(Integer[] a) {
		List<Integer> list = Arrays.asList(a);
		Collections.sort(list);
		return list.get(1);
	}
}
