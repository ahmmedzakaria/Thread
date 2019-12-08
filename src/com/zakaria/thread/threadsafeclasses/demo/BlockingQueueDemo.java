/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakaria.thread.threadsafeclasses.demo;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 *
 * @author Zakaria Ahmmed
 */
public class BlockingQueueDemo {
    private static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
    
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            try {
                producer();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        
        Thread t2 = new Thread(()->{
            try {
                consumer();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
    
    private  static void producer() throws InterruptedException{
        Random random =new Random();
        while(true){
            blockingQueue.put(random.nextInt(100));
        }
    }
    
     private  static void consumer() throws InterruptedException{
        Random random =new Random();
        while(true){
            Thread.sleep(100);
            if(random.nextInt(10) == 0){
                Integer value = blockingQueue.take();
                System.out.println("Taken value: "+ value+" Queue size is: "+blockingQueue.size());
            }
            
        }
    }
}
