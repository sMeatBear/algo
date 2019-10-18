package app.thread;

class MyRunnable implements Runnable {
    int i = 10;

    @Override
    public void run() {
        try {
            synchronized(this) {

                while (i >= 0) {
                    i--;
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    Thread.sleep(100);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

public class ThreadImpl {
    public static void main(String[] args) {
        Runnable r = new MyRunnable();
        
        Thread th1 = new Thread(r, "hello");
        Thread th2 = new Thread(r, "nihao");
        th1.start();
        th2.start();
    }
}