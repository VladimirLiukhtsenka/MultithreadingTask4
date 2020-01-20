package com.liukhtenko.multithreading.entity;

import java.util.concurrent.Callable;

public class Watcher implements Callable<Integer> {
    @Override
    public Integer call() {
        LogisticBase logisticBase = LogisticBase.getInstance();
        Integer result = logisticBase.freeTerminal.size() + logisticBase.busyTerminal.size();
        return result;
    }
}
