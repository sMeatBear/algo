package app.thread;

public class FourThreadPractice {
    int j;
    public static void main(String[] args) {
        FourThreadPractice f = new FourThreadPractice();
        // thread 1 and thread 2 j++
        // thread 3 and thread 4 j--
        Thread th1 = new Thread(() -> {
            synchronized (f) {
                while (f.j < 10) {
                    System.out.println(Thread.currentThread().getName() + " " + ++f.j);
                    try {
                        Thread.sleep(100);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }, "thread-1");

        Thread th2 = new Thread(() -> {
            synchronized (f) {
                while (f.j < 10) {
                    System.out.println(Thread.currentThread().getName() + " " + ++f.j);
                    try {
                        Thread.sleep(100);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }, "thread-2");

        Thread th3 = new Thread(() -> {
            synchronized (f) {
                while (f.j > 0) {
                    System.out.println(Thread.currentThread().getName() + " " + --f.j);
                    try {
                        Thread.sleep(100);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }, "thread-3");

        Thread th4 = new Thread(() -> {
            synchronized (f) {
                while (f.j > 0) {
                    System.out.println(Thread.currentThread().getName() + " " + --f.j);
                    try {
                        Thread.sleep(100);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }, "thread-4");

        th1.start();
        th2.start();
        th3.start();
        th4.start();
    }
}