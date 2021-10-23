package org.example;

import java.util.ArrayList;
import java.util.List;

public class PrintHelloFromThread implements Runnable {
    
    @Override
    public void run() {
        System.out.println("Hello from thread #" + Thread.currentThread().getName());
    }
    
    public void startMethod() {
        List<Thread> threads = new ArrayList<>();
        for (int i = 49; i >= 0; i--) {
            Thread t = new Thread(new PrintHelloFromThread());
            t.setName(String.valueOf(i));
            threads.add(t);
        }
        
        for (Thread t : threads) {
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
