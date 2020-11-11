package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Core {

    private final Queue<Process> processQueue;
    private final Random random = new Random();
    private final int qTime = 20;

    public Core() {
        processQueue = new LinkedList<>();
        System.out.println("Статус завершенности процессов: ");
        start();
        planning();
    }

    public void start() {
        int rndCountOfProcess = random.nextInt(5) + 1;
        for (int i = 0; i < rndCountOfProcess; i++) {
            System.out.println("На процесс " + i + " выделено следующее кол-во времени: " + qTime);
        }
        for (int i = 0; i < rndCountOfProcess; i++) {
            System.out.println("Процесс " + i + ": ");
            processQueue.add(new Process(i, qTime));
        }
    }

    public void planning() {
        while (!processQueue.isEmpty()) {
            Process obj = processQueue.poll();
            if(obj.planning()){
                System.out.println("Процесс " + obj.getProcessID() + " завершен");
            }
            else{
                System.out.println("Процесс " + obj.getProcessID() + " не завершен, помещение в очередь");
                obj.setProcessTime(qTime);
                processQueue.add(obj);
            }
        }
    }
}
