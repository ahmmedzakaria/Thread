/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakaria.thread.t01ExtendThread;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zakaria Ahmmed
 */
public class ThreadDemo1{
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        thread1.start();
        thread2.start();
    }
}

class MyThread extends Thread{

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