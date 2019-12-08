/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakaria.thread.t04Synchronization;

import java.util.Scanner;


/**
 *
 * @author Zakaria Ahmmed
 */
public class StopThreadWithFlag{
    public static void main(String[] args) {
        Processor process1 = new Processor();
        process1.start();
        
        System.out.println("Click the console & Press Enter to stop ...");
        Scanner scanner=new Scanner(System.in);
        String s=scanner.nextLine();
        
        process1.shoutdown();
        
    }
}

class Processor extends Thread{
    private boolean flag=true;
    @Override
    public void run() {
        while (flag) {
            try {
                System.out.println("Hello ");
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                 ex.printStackTrace();
            }
        }
    }
    
    void shoutdown(){
        System.out.println("Stopping ...");
        flag=false;
    }
}