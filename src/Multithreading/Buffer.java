package Multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Screen.MainPanel;


public class Buffer {

    private Queue<String> storage;

    private Lock lock = new ReentrantLock();
    private Condition bufferNotFull = lock.newCondition();
    private Condition bufferNotEmpty = lock.newCondition();

    private int bufferSize;
    private int numberOfConsumerOperations;
    private int numberOfProducerOperations;

    private MainPanel dashboard;

    public Buffer(int size, MainPanel dashboard) {
        // this.storage = new LinkedList<>(Arrays.asList(new String[length]));

        this.storage = new LinkedList<String>();
        this.bufferSize = size;
        this.dashboard = dashboard;

        this.numberOfConsumerOperations = 0;
        this.numberOfProducerOperations = 0;
    }

    public void produce(String product) throws InterruptedException {
        lock.lock();
        try {
            while (this.bufferSize == this.storage.size()) {
                bufferNotFull.await();
            }
            this.storage.add(product);
            numberOfProducerOperations++;
            dashboard.addRemainingCounter(numberOfProducerOperations);
            dashboard.addRemainingDividedByBufferSize(numberOfProducerOperations, bufferSize);
            dashboard.setBufferBarValue(numberOfProducerOperations, bufferSize);

            bufferNotEmpty.signal();
        } finally {
            try {
                lock.unlock();
            }
            catch (IllegalMonitorStateException e){
                System.out.println("already unlocked");
            }
        }
    }

    public String consume() throws InterruptedException {
        lock.lock();

        try {
            while (this.storage.isEmpty()) {
                bufferNotEmpty.await();
            }
            dashboard.addRemainingCounter(numberOfProducerOperations --);
            dashboard.addCompletedCounter(numberOfConsumerOperations ++);

            String product = this.storage.poll();

            bufferNotFull.signal();

            return product;
        } finally {
            lock.unlock();
        }
    }


}
