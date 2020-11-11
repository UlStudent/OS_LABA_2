package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Process {
    private final int processID;
    private final Queue<Thread> threadQueue;
    private final Random random = new Random();
    private int processTime;

    public Process(int processID, int processTime) {
        this.processID = processID;
        this.processTime = processTime;
        threadQueue = new LinkedList<>();
        start();
    }

    public void start() {
        int rndCountOfThreads = random.nextInt(5) + 1;
        for (int i = 0; i < rndCountOfThreads + 1; i++) {
            int rndTimeThread = random.nextInt(10);
            System.out.println("На поток " + i + " требуется следующее кол-во времени: " + rndTimeThread);
            threadQueue.add(new Thread(i, rndTimeThread));
        }
    }

    public boolean planning() {
        if(threadQueue.size() == 0){
            return false;
        }
        int threadTime = processTime/threadQueue.size();
        while (!threadQueue.isEmpty() && processTime > 0) {
            Thread obj = threadQueue.poll();
            if(threadTime >= processTime){
                threadTime = processTime;
            }
            int time = obj.startThread(threadTime);
            processTime -= threadTime;
            if (time <= 0) {
                System.out.println("Поток " + obj.getThreadID() + " выполнен успешно");
                processTime += Math.abs(time);
            }
            else {
                System.out.println("Поток " + obj.getThreadID() + " не успел выполниться, возвращение в очередь");
                threadQueue.add(obj);
            }
        }
        return threadQueue.isEmpty();
    }

    public int getProcessID() {
        return processID;
    }

    public void setProcessTime(int processTime) {
        this.processTime = processTime;
    }
}
