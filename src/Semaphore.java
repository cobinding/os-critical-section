public class Semaphore {
    private int value;

    public Semaphore(int value) {
        this.value = value;
    }

    // 고유 락을 가진 스레드
    public synchronized void acquire() {
        // 세마포어를 이용할 수 없는 상황
        while (value <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        // 세마포어가 이용 가능할 때
        value--;
    }

    public synchronized void release() {
        ++value;
        notify();
    }
}

/* java object 클래스의 wait과 notify

* wait(): 갖고 있던 고유 락을 해제ㅔ하고, 스레드를 잠들게한다. 호출하는 스레드는 반드시 고유 락을 갖고 있어야 하므로, synchronized 블록 내에서 호출되어야 한다.
* notify(): 잠들어 있던 스레드 중 임의로 하나를 골라 깨운다.
*
* 자바의 고유 락과 synchronized 블록
* 자바의 모든 객체는 락(lock)을 가지고 있다. 자바의 synchronized 블록은 동시성 문제를 해결하는
* 가장 간편한 방법으로 고유 락을 이용하여 여러 스레드의 접근을 제어한다. 만약 ++cnt;라는 코드가 있다고 헀을 때,
* ++ 연산자를 호출하면 변수를 메모리에서 읽고, 증가시키고, 다시 메모리에 쓰는 과정을 순차적으로 진행한다. 이는 동시성 프로그래밍에서 문제가 되는 전형적인
* read-modify-write 패턴이다. 두 스레드가 동시에 같은 값을 읽고, 값을 증가시켜 저장하면 동시성 문제가 발생한다. 이를 해결하기 위해 여러 스레드가 동시에 공유 변수에
* 접근하지 못하도록 synchronized(lock)을 제공한다.
* */
