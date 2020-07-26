
public class BinarySearch {
	private static int[] data = {2,5,8,10,43,67};
	private static int size = data.length;
	public static void main(String[] args) {
		System.out.println("found  " + binarySearch(67));
		System.out.println("found  " + binarySearch(56));
	}

	public static boolean binarySearch(int key) {
		int low = 0;
		int high = size - 1;

		while (high >= low) {
			int middle = (low + high) / 2;
			if (data[middle] == key) {
				return true;
			}
			if (data[middle] < key) {
				low = middle + 1;
			}
			if (data[middle] > key) {
				high = middle - 1;
			}
		}
		return false;
	}
}
