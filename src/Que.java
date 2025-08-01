
public class Que {

    Semaphore smpConsumer = new Semaphore(0);
    Semaphore smpProducer = new Semaphore(1);
    int item;

    void get() throws InterruptedException{
        smpConsumer.acquire(); // 생산
        System.out.println("Consumer consumed item: " + item);
        smpProducer.release(); // Consumer에게 알림
    }

    void put(int item) throws InterruptedException{
        smpProducer.acquire();
        System.out.println("Producer produced item: " + item);
        this.item = item;
        smpConsumer.release();

    }
}
