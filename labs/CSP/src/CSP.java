import java.util.concurrent.Semaphore;
import java.util.ArrayList;
import java.util.List;

public class CSP {

	public static void main(String[] args) {
		Runnable c1 = new Counter1();
		Runnable c2 = new Counter2();
		
		Thread t1 = new Thread(c1);
		Thread t2 = new Thread(c2);
		
		t1.start();
		t2.start();
	}
}