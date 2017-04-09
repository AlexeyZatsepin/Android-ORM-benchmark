package com.study.benchmarkorm;

public class SimpleProfiler {
    private long lastTime = System.currentTimeMillis();

    public void start() {
        lastTime = System.currentTimeMillis();
    }

    public long stop() {
        return System.currentTimeMillis() - lastTime;
    }
}
