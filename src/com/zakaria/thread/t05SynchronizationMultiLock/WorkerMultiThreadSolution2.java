/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakaria.thread.t05SynchronizationMultiLock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Zakaria 
 * Using synchronized block with tow different lock we able to get correct result in multiple run
 * And It is taking less time then previous solution
 * We need a batter solution
 */
public class WorkerMultiThreadSolution2 {

    private Random random = new Random();
    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();
    
    Object lock1 = new Object();
    Object lock2 = new Object();

    void stageOne() {
       synchronized(lock1){
            try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        list1.add(random.nextInt());
       }
    }

   void stageTow() {
        synchronized(lock2){
            try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        list2.add(random.nextInt());
        }
    }

    void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTow();
        }
    }

    public void startWork() {
        System.out.println("Starting...");

        long start = System.currentTimeMillis();
        Thread t1=new Thread(()-> process());
        Thread t2=new Thread(()-> process());
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("Time take: " + (end - start));
        System.out.println("List1: " + list1.size() + " List2: " + list2.size());
    }
    
    public static void main(String[] args) {
        new WorkerMultiThreadSolution2().startWork();
    }
}
