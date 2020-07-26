
public class ArraysRotationCount {

	public static void main(String[] args) {
		int[] array = {15,20,30,42,44,5,6,8,10};
		System.out.println("Array is rotated :" + findRotationCount(array,array.length) + " times");
	}

	private static int findRotationCount(int[] array,int arrayLength) {
		int low = 0;
		int high  = arrayLength - 1;
		while (high >= low) {
			if (array[low] <= array[high]) {
				return low; // Case 1
			}
			int mid = (low+ high) /2;
			int next = (mid + 1) % arrayLength;
			int prev = (mid + arrayLength -1)  % arrayLength;
			if (array[mid] <= array[next] && array[mid] <= array[prev]) {
				return mid; //Case 2
			} else if (array[mid] <= array[high]) {
				high = mid - 1; // Case 3
			} else if (array[mid] >= array[low]) {
				low = mid + 1; // Case 4
			}
			
		}
		return -1;
	}
}
