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
 * Using synchronized key word able to get correct result in multiple run
 * But It is taking more time then previous
 * Because synchronized method create intrinsic lock on WorkerMultiThreadSolution object
 * If one thread run synchronized method other thread have to wait till running thread release the lock
 * 
 * We need a batter solution
 * Here tow method stageOne() and stageTwo() are independent. They are writing two different list individually.
 * So we can create two different lock for each method.
 */
public class WorkerMultiThreadSolution {

    private Random random = new Random();
    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    synchronized void stageOne() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        list1.add(random.nextInt());
    }

   synchronized void stageTow() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        list2.add(random.nextInt());
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
        new WorkerMultiThreadSolution().startWork();
    }
}
