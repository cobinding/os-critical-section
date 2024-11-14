public class Producer implements Runnable {
    Que q;

    Producer(Que q) {
        this.q = q;
        // Runnable interface를 통해 Producer Thread 생성.
        new Thread(this, "Producer").start();
    }

    // Runnable 구현체의 run() 함수는 반드시 override해서 사용해야 한다.
    @Override
    public void run() {
        // producer put items
        for (int i = 1; i < 5; i++) {
            try { q.put(i); }
            catch (InterruptedException e) { throw new RuntimeException(e);}
        }
    }
}
