package practise;

public class InsertElementInSortedArray {

	// Inserts a key in arr[] of given
	// capacity. n is current size of arr[].
	// This function returns n+1 if insertion
	// is successful, else n.
	static int insertSorted(int arr[], int arrayLength, int key, int capacity) {
		// Cannot insert more elements if n is already
		// more than or equal to capcity
		if (arrayLength >= capacity)
			return arrayLength;

		int i;
		for (i = arrayLength - 1; (i >= 0 && arr[i] > key); i--)
			arr[i + 1] = arr[i];

		arr[i + 1] = key;

		return (arrayLength + 1);
	}

	/* Driver program to test above function */
	public static void main(String[] args) {
		int arrayLength = 6;
		int arr[] = new int[arrayLength + 1];
		int capacity = arr.length;

		arr[0] = 12;
		arr[1] = 16;
		arr[2] = 20;
		arr[3] = 40;
		arr[4] = 50;
		arr[5] = 70;

		int key = 26;

		System.out.print("\nBefore Insertion: ");
		for (int i = 0; i < arrayLength; i++)
			System.out.print(arr[i] + " ");

		// Inserting key
		arrayLength = insertSorted(arr, arrayLength, key, capacity);

		System.out.print("\nAfter Insertion: ");
		for (int i = 0; i < arrayLength; i++)
			System.out.print(arr[i] + " ");
	}
	static int insertSorted1(int arr[], int arrayLength, int key, int capacity) {
		if (arrayLength > capacity) {
			return arrayLength;
		}
		int i;
		for (i = arrayLength -1 ; (i >=0 && arr[i] > key); i--) {
			arr[i + 1]= arr[i];
		}
		arr[ i + 1 ] = key;
		return arrayLength + 1;
	}
	

}
