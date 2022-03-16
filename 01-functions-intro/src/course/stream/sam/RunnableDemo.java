package course.stream.sam;

class RocketLiftOff implements Runnable {
    private int n;

    public RocketLiftOff(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        for (int i = 10; i > 0; i--) {
            System.out.printf("Rocket %d: Countdown: %d%n", n, i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.printf("Countdown interrupted for rocket %d%n", n);
            }
        }
        System.out.printf("Rocket %d: Liftoff!!!%n", n);
    }
}

public class RunnableDemo {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new Thread(new RocketLiftOff(i)).start();
        }
    }
}
