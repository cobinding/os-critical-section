public class Consumer implements Runnable {
    Que q;
    Consumer(Que q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try { q.get(); }
            catch (InterruptedException e) { throw new RuntimeException(e); }
        }
    }
}
