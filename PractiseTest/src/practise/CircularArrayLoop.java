package practise;

import java.util.HashSet;
import java.util.Set;

public class CircularArrayLoop {

	public static void main(String[] args) {
		int[] arr = { 2, -1, 1, 2, 2 };
		System.out.println(circularArrayLoop1(arr));
		int[] arr1 = { 1, 1, 1, 1, 1, 1 };
		System.out.println(circularArrayLoop1(arr1));
		int[] arr2 = { 1, 2 };
		System.out.println(circularArrayLoop1(arr2));
		int[] arr3 = {1,1,1,2};
		System.out.println(circularArrayLoop3(arr3));
	}

	public static boolean circularArrayLoop(int[] nums) {
		if (nums == null || nums.length < 2)
			return false;
		boolean forward = nums[0] > 0 ? true : false;
		int slow = getNext(0, nums);
		int fast = getNext(slow, nums);
		while (slow != fast && slow != 0) {
			if (nums[slow] < 0 && forward)
				return false;
			slow = getNext(slow, nums);
			fast = getNext(fast, nums);
			fast = getNext(fast, nums);
		}
		return slow == 0;
	}

	private static int getNext(int index, int[] nums) {
		return (index + nums[index] + nums.length) % nums.length;
	}
	

    public static boolean circularArrayLoop3(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            Set<Integer> visited = new HashSet<>();
            boolean isPos = nums[i] > 0;
            boolean res = check(nums, i, visited, isPos);
            if (res) {
                return true;
            }
            setZero(visited, nums);
        }
        return false;
    }
    
    private static void setZero(Set<Integer> visited, int[] nums) {
        for (int i : visited) {
            nums[i] = 0;
        }
    }
    
    private static boolean check(int[] nums, int index, Set<Integer> visited, boolean isPos) {
        int len = nums.length;
        while (!visited.contains(index)) {
            int n = nums[index];
			if (n == 0) {
				return false;
			}
            if((n > 0) != isPos) {
                return false;
            }
            int newIdx = (index + n + len) % len;
            if (newIdx == index) {
                return false;
            }
            visited.add(index);
            index = newIdx;
        }
        return true;
    }
    public static boolean circularArrayLoop1(int[] nums) {
		if (nums == null || nums.length < 2) {
			return false;
		}
		boolean forward = nums[0] > 0 ? true : false;
		int slow = getNext(0,nums);
		int fast = getNext(slow,nums);
		while(slow != fast && slow != 0) {
			if (forward && nums[slow] < 0) {
				return false;
			}
			slow = getNext(slow,nums);
			fast = getNext(fast,nums);
			fast = getNext(fast,nums);
		}
		return slow == 0;
	}
}
