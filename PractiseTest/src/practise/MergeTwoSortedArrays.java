package practise;

import java.util.ArrayList;
import java.util.List;

public class MergeTwoSortedArrays {

	public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7}; 
        List<Integer> list1 = new ArrayList<Integer>();
        for (int i : arr1)
        {
        	list1.add(i);
        }
      
        int[] arr2 = {2, 4, 6, 8}; 
        List<Integer> list2 = new ArrayList<Integer>();
        for (int i : arr2)
        {
            list2.add(i);
        }

		int[] array = toIntArray(mergeList(list1,list2));
		
        int[] arr3 = mergeArrays(arr1, arr2, arr1.length,arr2.length); 
      
        System.out.println("Array after merging"); 
        for (int i=0; i < arr3.length; i++) 
            System.out.print(arr3[i] + " "); 
	}

	private static int[] toIntArray(List<Integer> list){
		  int[] ret = new int[list.size()];
		  for(int i = 0;i < ret.length;i++)
		    ret[i] = list.get(i);
		  return ret;
		}
	private static List<Integer> mergeList(List<Integer> list1, List<Integer> list2) {
		if (list1.size() == 0)
			return list2;
		if (list2.size() == 0)
			return list1;

		List result = new ArrayList<Integer>();

		if (list1.get(0) < list2.get(0))
			result.add(list1.remove(0));
		else
			result.add(list2.remove(0));

		result.addAll(mergeList(list1, list2));

		return result;
	}
	
	/**
	 * 
	 * @param arr1
	 * @param arr2
	 * @param l1
	 * @param l2
	 * @return
	 */
    public static int[] mergeArrays(int[] arr1, int[] arr2,int l1,int l2) 
    { 
       int i = 0,j = 0 , k = 0;
       int[] arr3 = new int[l1 + l2];
        while (i < l1 && j < l2) {
            // Check if current element of first 
            // array is smaller than current element 
            // of second array. If yes, store first 
            // array element and increment first array 
            // index. Otherwise do same with second array 
          if (arr1[i] < arr2[j]) {
            arr3[k++] = arr1[i++];
          } else {
            arr3[k++] = arr2[j++];
          }
        }
      while (i < l1) {
        arr3[k++] = arr1[i++];
      }
      while (j < l2) {
        arr3[k++] = arr2[j++];
      }
      return arr3;
    } 
    
    public static int[] mergeArrays1(int[] arr1, int[] arr2) 
    { 
       int l1 = arr1.length;
       int l2 = arr2.length;
       int i = 0, j =0 , k =0;
       int[] arr3 = new int[l1 + l2];
       while (i < l1 && j < l2) {
    	   if (arr1[i] < arr2[j]) {
    		  arr3[k++]  = arr1[i++];
    	   } else {
    		  arr3[k++]  = arr2[j++];
    	   }
       }
       while (i < l1) {
    	   arr3[k++]  = arr1[i++];
       }
       while (j < l2) {
    	   arr3[k++]  = arr2[j++];
       }
       return arr3;
    } 
}
