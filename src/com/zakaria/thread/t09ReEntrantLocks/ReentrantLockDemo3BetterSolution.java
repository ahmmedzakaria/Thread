/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakaria.thread.t09ReEntrantLocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Zakaria Ahmmed
 */

public class ReentrantLockDemo3BetterSolution {
    
    /**
     * It will give correct result in each run
     */
    static class Runner {

            private int count = 0;
            private Lock lock = new ReentrantLock();

            void countStatus() {
                System.out.println("Count: " + count);
            }

            private void increment() {
                for (int i = 0; i < 10000; i++) {
                    count++;
                }
            }

            void firstThread() {
                lock.lock();
                try{
                    increment();
                }finally{
                    lock.unlock();
                }
            }

            void secondThread() {
                lock.lock();
                try{
                    increment();
                }finally{
                    lock.unlock();
                }
            }
        }
    
    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();
        
        Thread t1 = new Thread(()->{runner.firstThread();});
        Thread t2 = new Thread(()->{runner.secondThread();});
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        
        runner.countStatus();
   
    }
}
