package com.study.benchmarkorm;

public interface TestController {
    long testRead(int instances);
    long testWrite(int instances);
    long testUpdate(int instances);
    long testDelete(int instances);
}
