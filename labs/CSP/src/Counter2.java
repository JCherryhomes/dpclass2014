public class Counter2 extends Counter {
	@Override
	public void run() {
		while (counter < 100) {
			counter++;
			System.out.println("Counter 2: " + counter);
		}
	}
}