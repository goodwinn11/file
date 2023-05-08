import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        int numThreads = 5;
        Thread[] threads = new Thread[numThreads];
        
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(new IncrementTask(counter));
            threads[i].start();
        }
        
        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
        }
        
        System.out.println("Final counter value: " + counter.get());
    }
}

class IncrementTask implements Runnable {
    private AtomicInteger counter;
    
    public IncrementTask(AtomicInteger counter) {
        this.counter = counter;
    }
    
    public void run() {
        for (int i = 0; i < 100000; i++) {
            synchronized (counter) {
                counter.incrementAndGet();
            }
        }
    }
}
