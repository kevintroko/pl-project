package Logic;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import Screen.MainPanel;

/** @Author Valentin Ochoa */


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

        this.storage = new LinkedList<>();
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

            dashboard.addremainingBuffer(numberOfProducerOperations, bufferSize);
            dashboard.setBufferBarValue(numberOfProducerOperations, bufferSize);

        } finally {
                bufferNotEmpty.signal();
                lock.unlock();
        }
    }

    public String consume() throws InterruptedException {
        lock.lock();

        try {
            while (this.storage.isEmpty()) {
                bufferNotEmpty.await();
            }
            String product = this.storage.poll();
            dashboard.addRemainingCounter(numberOfProducerOperations --);
            dashboard.addCompletedCounter(numberOfConsumerOperations ++);


            return product;
        } finally {
            bufferNotFull.signal();
            lock.unlock();
        }
    }


}
