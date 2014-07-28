import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.*;

public class SudokuServer {
    static int PORT = -1;
    static ArrayList<Thread> cache;
    static ArrayList<Thread> runningCache;

    // no matter how many concurrent requests you get,
    // do not have more than three solvers running concurrently
    final static int MAXPARALLELTHREADS = 3;

    // this method exists for testing purposes, since
    // we want to clear out the singleton cache for
    // subsequent junit tests.
    static void resetcache() {
        cache.clear();
        runningCache.clear();
    }

    public static void start(int portNumber) throws IOException {
        cache = new ArrayList<>();
        runningCache = new ArrayList<>();
        PORT = portNumber;

        Runnable serverThread = new ThreadedSudokuServer();
        Thread t = new Thread(serverThread);
        System.out.println("Server running on port " + PORT);
        t.start();
    }
}

//TODOBEGIN(DP)
class ThreadedSudokuServer implements Runnable {
    public void run() {
        System.out.println("Running");
    }
}
//TODOEND(DP)
