import implementations.Queue;

public class Main {
    public static void main(String[] args) {

        Queue myQueue = new Queue();

        myQueue.offer(5);
        myQueue.offer(10);

        myQueue.poll();

        System.out.println();
    }
}
