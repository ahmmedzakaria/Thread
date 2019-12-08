/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakaria.thread.t09ReEntrantLocks;


/**
 *
 * @author Zakaria Ahmmed
 */

public class ReentrantLockDemo1 {
   
    /**
     * It will give different result in each run
     */
    static class Runner {

            private int count = 0;

            void countStatus() {
                System.out.println("Count: " + count);
            }

            private void increment() {
                for (int i = 0; i < 10000; i++) {
                    count++;
                }
            }

            void firstThread() {
                increment();
            }

            void secondThread() {
                increment();
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
