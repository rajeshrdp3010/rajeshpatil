package practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSets {
	public static void main(String[] args) {
		int[] array = {1,23,67,34,12,45};
		System.out.println(calculateSubset(array));
	}
	
	public static List<List<Integer>> calculateSubset(int[] array) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		Arrays.sort(array);
		backtrack(list,new ArrayList<Integer>(),array,0);
		return list;
	}
	public static void backtrack(List<List<Integer>> list,List<Integer> tempList,int[] array,int start) {
		list.add(new ArrayList<Integer>(tempList));
		for (int i = start;i< array.length;i++) {
			tempList.add(array[i]);
			backtrack(list,tempList,array,i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}
}
