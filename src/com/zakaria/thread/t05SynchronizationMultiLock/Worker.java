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
 * @author Zakaria Ahmmed
 */
public class Worker {

    private Random random = new Random();
    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    void stageOne() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        list1.add(random.nextInt());
    }

    void stageTow() {
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
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("Time take: " + (end - start));
        System.out.println("List1: " + list1.size() + " List2: " + list2.size());
    }
    
    public static void main(String[] args) {
        new Worker().startWork();
    }
}
