/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakaria.thread.t07WaitAndNotify;

import java.util.Scanner;

/**
 *
 * @author Zakaria Ahmmed
 */
class Processor{
    public void produce() throws InterruptedException{
        synchronized(this){
            System.out.println("Producer thread running ....");
            wait();
            System.out.println("Resumed.");
        }
    }
    public void consumer() throws InterruptedException{
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);
        synchronized(this){
            System.out.println("Waiting for return key (click console and press Enter)");
            scanner.nextLine();
            System.out.println("Return key pressed");
            notify();
//            System.out.println("Sleeping ... ");
//            Thread.sleep(5000); 


            
        }
    }
   
}
public class WaitNotifyDemo {
    
    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();
        Thread t1 = new Thread(()->{
            try {
                processor.produce();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        
        Thread t2 = new Thread(()->{
            try {
                processor.consumer();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
