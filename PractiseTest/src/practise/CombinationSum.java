package practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

	public static void main(String[] args) {
		int num = 3;
		int[] array = new int[num];
		for (int i = 0 , j = 1 ; i < num;i++) {
			array[i] = j++;
		}
		System.out.println(combinationSum1(array,num));
		List<List<Integer>> list = combinationSum1(array,num);
		int[][] twoDimArray = new int[list.size()][];
		int i = 0;
		for (List<Integer> innerList : list) {
			int[] innerArray = new int[innerList.size()];
			twoDimArray[i++] = innerArray;
			int j = 0;
			for (Integer number : innerList) {
				innerArray[j++] = number;
			}
		}
		System.out.println(twoDimArray);
	}
    private static List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        backtrack(resultList, new ArrayList<>(), nums, 0, target);
        return resultList;
    }
    private static void backtrack(List<List<Integer>> resultList,List<Integer> tempList,int[] nums,int start,int remain){
       if(remain < 0) {
          return; 
       } else if (remain == 0) {
           resultList.add(new ArrayList<>(tempList));
       } else {
           for (int i = start;i< nums.length;i++) {
               tempList.add(nums[i]);
               backtrack(resultList, tempList, nums, i , remain - nums[i]);
               tempList.remove(tempList.size() - 1);
           }
       }
    }
    
    private static List<List<Integer>> combinationSum1(int[] array,int k) {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        Arrays.sort(array);
        backtrack1(resultList,new ArrayList<>(),array,k);
        return resultList;
      }
      private static void backtrack1(List<List<Integer>> resultList,List<Integer> tempList,int[] array,int remain) {
        if (remain < 0) {
          return;
        } else if (remain == 0 && tempList.size() > 1) {
          resultList.add(new ArrayList<>(tempList));
        } else {
          for (int i = 0; i < array.length; i ++) {
            tempList.add(array[i]);
            backtrack1(resultList,tempList,array,remain - array[i]);
            tempList.remove(tempList.size() - 1);
          }
        }
      }
}
