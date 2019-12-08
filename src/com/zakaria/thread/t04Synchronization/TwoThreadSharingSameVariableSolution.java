/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakaria.thread.t04Synchronization;


/**
 *
 * @author Zakaria Ahmmed
 */
public class TwoThreadSharingSameVariableSolution {
  public static void main(String[] args) {
        App2 app2 =new App2();
        app2.startWork();
        
    }
    
}
class App2{
         int count = 0;
        synchronized void increment(){
            count++;
        }
       void startWork(){
            Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        
        System.out.println("Total Count: "+count);
       }
    }