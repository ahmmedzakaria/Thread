/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakaria.thread.t09ReEntrantLocks;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zakaria Ahmmed
 */

public class ReentrantLockDemo4AndCondition {
    
    /**
     * It will give correct result in each run
     */
    static class Runner {

            private int count = 0;
            private Lock lock = new ReentrantLock();
            private Condition cond = lock.newCondition();

            void countStatus() {
                System.out.println("Count: " + count);
            }

            private void increment() {
                for (int i = 0; i < 10000; i++) {
                    count++;
                }
            }

            void firstThread() throws InterruptedException {
                lock.lock();
                System.out.println("Waiting ...");
                cond.await(); // same as wait()
                System.out.println("Woken up!");
                try{
                    increment();
                }finally{
                    lock.unlock();
                }
            }

            void secondThread() throws InterruptedException {
                Thread.sleep(1000);
                lock.lock();
                System.out.println("Press the return key! (click console and press Enter)");
                new Scanner(System.in).nextLine();
                System.out.println("Got return key!");
                cond.signal();
                try{
                    increment();
                }finally{
                    lock.unlock();
                }
            }
        }
    
    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();
        
        Thread t1 = new Thread(()->{try {
            runner.firstThread();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
});
        Thread t2 = new Thread(()->{try {
            runner.secondThread();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
});
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        
        runner.countStatus();
   
    }
}
