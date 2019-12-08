/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakaria.thread.threadsafeclasses.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zakaria Ahmmed
 */
class ProcessCount extends Thread{
    CountDownLatch latch;

    public ProcessCount(CountDownLatch latch) {
        this.latch=latch;
    }
    
    
    @Override
    public void run() {
        System.out.println("Started: "+ Thread.currentThread().getName());
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
           ex.printStackTrace();
        }
        System.out.println("count:"+latch.getCount()+currentThread().getName()+"\n");
    }

}
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch latch =new CountDownLatch(3);
        ExecutorService executor =Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executor.submit(new ProcessCount(latch));
        }
        try {
            latch.await();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Completed");
    }
    
    
    
    
}

