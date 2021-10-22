package org.example;

public class PrintHelloFromThread extends Thread {

    PrintHelloFromThread(Integer numberOfThread) {
        super(String.valueOf(numberOfThread));
    }

    @Override
    public void run() {
        int numberOfThread = Integer.parseInt(this.getName());
        if (numberOfThread <= 49) {
            PrintHelloFromThread printHelloFromThread = new PrintHelloFromThread(numberOfThread + 1);
            printHelloFromThread.start();
            try {
                printHelloFromThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hello from thread #" + numberOfThread);
        }
    }
}
