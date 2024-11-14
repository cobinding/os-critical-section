public class Main {
    public static void main(String[] args) {

        Que q  = new Que();

        new Consumer(q);
        new Producer(q);
    }
}