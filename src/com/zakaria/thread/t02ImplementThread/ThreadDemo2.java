/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakaria.thread.t02ImplementThread;

/**
 *
 * @author Zakaria Ahmmed
 */
public class ThreadDemo2{
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyThread());
        Thread thread2 = new Thread(new MyThread());
        thread1.start();
        thread2.start();
    }
}

class MyThread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Hello "+i);
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                 ex.printStackTrace();
            }
        }
    }
}