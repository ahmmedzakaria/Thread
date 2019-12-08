/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakaria.thread.t06ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zakaria Ahmmed
 */

class Processor implements Runnable{
    private int id;
    public Processor(int id) {
        this.id =id;
    }
   
    @Override
    public void run() {
        System.out.println("Starting "+ id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Completed "+ id);
    }

}
public class App {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Processor(i));
        }
        
        executorService.shutdown();
        System.out.println("All Task Submited to executorService");
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("All Task Completed ....");
    }
}
