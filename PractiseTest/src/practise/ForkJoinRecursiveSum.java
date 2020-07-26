package practise;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
/**
 * This program recursively finds the sum of an array in parallel using Java's
 * ForkJoin Framework. This example is from Dan Grossman's A Sophomoric
 * Introduction to Shared-Memory Parallelism and Concurrency, Chapter 3.
 */
@SuppressWarnings("serial")
public class ForkJoinRecursiveSum extends RecursiveTask<BigInteger> {
	public static final int SEQUENTIAL_THRESHOLD = 10;

	private int lo, hi;
	private long[] arr;

	public ForkJoinRecursiveSum(long[] arr, int lo, int hi) {
		this.lo = lo;
		this.hi = hi;
		this.arr = arr;
	}

	@Override
	public BigInteger compute() {
		if (hi - lo <= SEQUENTIAL_THRESHOLD) {
			long ans = 0;
			for (int i = lo; i < hi; i++) {
				ans += arr[i];
			}
			return BigInteger.valueOf(ans);
		} else {
			int mid = (lo + hi) / 2;
			ForkJoinRecursiveSum left = new ForkJoinRecursiveSum(arr, lo, mid);
			ForkJoinRecursiveSum right = new ForkJoinRecursiveSum(arr, mid, hi);
			left.fork();
			BigInteger rightAns = right.compute();
			BigInteger leftAns = left.join();
			return leftAns.add(rightAns);
		}
	}

	/**
	 * Pool of worker threads.
	 */
	private static final ForkJoinPool fjPool = new ForkJoinPool();

	/**
	 * Sum the elements of an array.
	 * 
	 * @param arr
	 *            array to sum
	 * @return sum of the array's elements
	 * @throws InterruptedException
	 *             shouldn't happen
	 */
	public static BigInteger sum(long[] arr) throws InterruptedException {
		return fjPool.invoke(new ForkJoinRecursiveSum(arr, 0, arr.length));
	}

	public static void main(String[] args) throws InterruptedException {
		long[] arr = new long[10000000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		BigInteger sum = sum(arr);
		System.out.println("Sum: " + sum);
	}
}
