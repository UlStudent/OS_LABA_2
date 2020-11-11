package com.company;

public class Thread {
    private int threadID;
    private int threadTime;

    public Thread(int threadID, int threadTime) {
        this.threadID = threadID;
        this.threadTime = threadTime;
    }

    public int startThread(int currentTime) {
        threadTime -= currentTime;
        return threadTime;
    }

    public int getThreadID() {
        return threadID;
    }
}
