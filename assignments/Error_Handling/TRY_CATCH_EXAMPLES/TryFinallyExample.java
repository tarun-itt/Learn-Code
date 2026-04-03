import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryFinallyExample {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        try {
            performCriticalTask();
        } catch (Exception e) {
            System.out.println("Caught in main: " + e.getMessage());
        }
    }

    private static void performCriticalTask() {
        lock.lock();
        System.out.println("Lock acquired.");
        
        try {
            throw new RuntimeException("Error during critical operation");
        } finally {
            // Always release the lock to prevent deadlocks
            lock.unlock();
            System.out.println("Lock released.");
        }
    }
}
