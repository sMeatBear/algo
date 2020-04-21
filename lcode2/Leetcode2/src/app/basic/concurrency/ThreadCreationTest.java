package app.basic.concurrency;

class GcThread extends Thread {
    @Override
    public void run() {
        System.out.println("Garbage collector " + Thread.currentThread().getName() +  " is working.");
    }
}

class MyClient implements Runnable {

    @Override
    public void run() {
        System.out.println("Client " + Thread.currentThread().getName() + " is running.");
    }

}

public class ThreadCreationTest {
    public static void main(String[] args) {
        Thread gcThread = new GcThread();
        Thread clientThread = new Thread(new MyClient());
        gcThread.start();
        clientThread.start();
    }
}