

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestException4 {

	public void start() throws IOException {		
		System.out.println("start in base class");
	}
	
	public void foo() throws NullPointerException {
		System.out.println("foo in base class");
	}
	public static void main(String[] args) {
		TestException4 test  = new TestException5();
		try {
			test.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.foo();
		
	}
}

class TestException5 extends TestException4 {
	@Override
	public void start() throws FileNotFoundException {
		System.out.println("start in child class");
	}
	@Override
	public void foo() throws RuntimeException {
		System.out.println("foo in chid class");
	}
}
