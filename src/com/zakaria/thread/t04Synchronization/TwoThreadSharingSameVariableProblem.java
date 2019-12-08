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

/**
 * Run Multiple time, you will see multiple result
 * 
 */
public class TwoThreadSharingSameVariableProblem {

    public static void main(String[] args) {
        App app =new App();
        app.startWork();
        
    }
    
}
class App{
         int count = 0;
        
       void startWork(){
            Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count++;
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