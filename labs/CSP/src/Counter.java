
public abstract class Counter implements Runnable {
	public static int counter = 0;
	public abstract void run();
}