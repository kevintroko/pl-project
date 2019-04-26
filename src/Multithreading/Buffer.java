package Multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Screen.MainPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//TODO Refactor this
 public class Buffer {
    private int bufferLength,
    			consumerOperations, 
    			producerOperations;    
    private Queue<String> bufferStorage;
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    private MainPanel mainPanel;
    
    public Buffer(int length, MainPanel mainPanel) {
        this.bufferStorage = new LinkedList<String>();
        this.mainPanel = mainPanel;
        this.bufferLength = length;
        this.consumerOperations = 0;
        this.producerOperations = 0;
    }
    
    public String consume() throws InterruptedException {
    	System.out.println(bufferStorage.size());
    	lock.lock();
    	try {
	         while(this.bufferStorage.isEmpty()) {
	        	 notEmpty.await();
	         }
	         String product = this.bufferStorage.poll();
		     consumerOperations++;
		     producerOperations--;
		     mainPanel.addRemainingCounter(producerOperations);
	         mainPanel.addCompletedCounter(consumerOperations);
	         notFull.signal();
		     return product;
    	} finally {
			 lock.unlock();
    	}    
    }
    
    public void produce(String product) throws InterruptedException {
    	lock.lock();
        try {
            while(this.bufferLength == this.bufferStorage.size()) {
                notFull.await();
            }
            this.bufferStorage.add(product);
            producerOperations++;
            mainPanel.addRemainingCounter(producerOperations);
            mainPanel.addRemainingDividedByBufferSize(producerOperations, bufferLength);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }  
    }    
}
