package practise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		int[] arr = { 2, -1, 1, 2, 2 };
		System.out.println(circularArrayLoop(arr));
		int[] arr1 = { 1, 1, 1, 1, 1, 1 };
		System.out.println(circularArrayLoop(arr1));
		int[] arr2 = { 1, 2 };
		System.out.println(circularArrayLoop(arr2));
		int[] arr3 = {1,1,1,2};
		System.out.println(circularArrayLoop(arr3));
	}

	public static boolean circularArrayLoop(int[] nums) {
		if (nums == null || nums.length < 2) {
			return false;
		}
		boolean forward = nums[0] > 0 ? true : false;
		int slow = getNext(0, nums);
		int fast = getNext(slow,nums);
		while (slow != fast && slow != 0) {
			if (nums[slow] < 0 && forward) {
				return false;
			}
			slow = getNext(slow, nums);
			fast = getNext(fast, nums);
			fast = getNext(fast, nums);
		}
		return slow == 0;
	}
	
	private static int getNext(int index, int[] nums) {
		return (index + nums[index] + nums.length) % nums.length;
	}
}
