/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakaria.thread.t08LowLevelSynchronizationWaitNodify;


import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Zakaria Ahmmed
 */
class Processor{
    LinkedList<Integer> linkedList = new LinkedList<>();
    private final int LIMIT = 5;
    private final int SLEEP_LIMIT = 1000;
    private Object lock = new Object();
    Random random  = new Random();
    
    void produce ()throws InterruptedException{
        int value=0;
        while(true){
            synchronized(lock){
                while(linkedList.size() == LIMIT){
                    System.out.println("produce waiting ...");
                    lock.wait();
                }
                linkedList.add(value++);
                lock.notify();
            }
            Thread.sleep(random.nextInt(SLEEP_LIMIT));
        }
    }
    
    void consumer ()throws InterruptedException{
        
        while(true){
            synchronized(lock){
                while(linkedList.size() == 0){
                    System.out.println("consumer waiting ...");
                    lock.wait();
                }
                System.out.print("Size of List: "+linkedList.size());
                int value = linkedList.removeFirst();
                System.out.println("; and First Value is: "+ value);
                lock.notify();
            }
            Thread.sleep(random.nextInt(SLEEP_LIMIT));
        }
    }
}

public class WaitNotifyLowLevelSync {
    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();
        Thread t1 = new Thread(()->{
            try {
                processor.produce();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        
        Thread t2 = new Thread(()->{
            try {
                processor.consumer();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
