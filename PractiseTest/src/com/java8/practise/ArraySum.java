package com.java8.practise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ArraySum {

	public static void main(String[] args) {
		int[] array = new int[] {-1, 4, -2};
		List<List<Integer>> resultList = generatePerm(array,0);
		System.out.println(resultList);
		List<Integer> sumList = resultList.stream().map(innerList -> {
			Integer val = innerList.stream().mapToInt(i -> i).sum();
			return val;
		}).collect(Collectors.toList());
		System.out.println(resultList.get(sumList.indexOf(Collections.max(sumList))));
	}

	private static List<List<Integer>> generatePerm(int[] nums,int target) {
		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		permutations(resultList,new ArrayList<>(),nums,target);
		return resultList;
	}
	private static void permutations(List<List<Integer>> resultList,List<Integer> tempList,int[] nums,int start) {
		if (!tempList.isEmpty()) {
			resultList.add(new ArrayList<>(tempList));
		}
		for (int i = start; i < nums.length ; i++) {
			tempList.add(nums[i]);
			permutations(resultList,tempList,nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}
}
