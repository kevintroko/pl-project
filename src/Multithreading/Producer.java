package Multithreading;

import java.util.logging.Level;
import java.util.logging.Logger;

import Screen.Dashboard;


public class Producer extends Thread {
    
    private Buffer buffer;
    private boolean isStart;
    private Product randomOperations;
    private long sleepTime;
    private Dashboard dashboard;
    
    public Producer(Buffer buffer,Dashboard dashboard, int sleepTime) {
        this.buffer = buffer;
        this.isStart = true;
        this.sleepTime = sleepTime;
        this.dashboard = dashboard;
        this.randomOperations = new Product();
    }
    
    @Override
    public void run() {
       System.out.println("Running producer...");
       
       while (isStart) {
    	   String product = this.randomOperations.createOperation();
           try {
			this.buffer.produce(product);
           } catch (InterruptedException e1) {
        	   e1.printStackTrace();
           }
           System.out.println("Producer produced: " + product);
           dashboard.addElementToRemainingList("Producer produced: " + product);
           try {
               Thread.sleep(sleepTime);
           } catch(InterruptedException e) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, e);
           }
       }
    }
    
    public void setCancel() {
    	this.isStart = false;
    }
    
}
