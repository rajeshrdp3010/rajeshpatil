import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;


public class LongAdderTest {

	public static void main(String[] args) {
		final long BILLION = 1000000000;
		long answer = BILLION *(BILLION + 1)/2;
		System.out.println(answer);
		LongAdder a = new LongAdder();
		List<Integer> myList = new ArrayList<Integer>(10);
		for(int i = 0;i<10;i++) {
			myList.add(i);
		}
		myList.parallelStream().forEach(a::add);
		long sum = a.longValue();
		System.out.println(sum);
		
	}

}
