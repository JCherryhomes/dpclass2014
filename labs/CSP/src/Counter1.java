public class Counter1 extends Counter {
	@Override
	public void run() {
		while (counter < 100) {
			counter++;
			System.out.println("Counter 1: " + counter);
		}
	}
}