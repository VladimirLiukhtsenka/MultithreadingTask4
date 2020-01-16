package com.liukhtenko.multithreading.entity;

import java.util.TimerTask;
import java.util.concurrent.Callable;

public class Watcher implements  Callable <Integer>  {
    @Override
    public Integer call() throws Exception {
        LogisticBase logisticBase = LogisticBase.getInstance();
        Integer result = logisticBase.freeTerminal.size() + logisticBase.busyTerminal.size();
//        if (result< LogisticBase.NUMBER_OF_TERMINALS){
//            logisticBase.freeTerminal.offer(new Terminal(911));
//        }
        return result;
    }

}
