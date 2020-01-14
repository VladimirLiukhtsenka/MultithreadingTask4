package com.liukhtenko.multithreading.service;

import com.liukhtenko.multithreading.entity.LogisticBase;
import com.liukhtenko.multithreading.entity.Van;

public class VanService extends Thread {
LogisticBase logisticBase = LogisticBase.getInstance();
    private Van van;

    public VanService (Van van){
        this.van = van;
    }

    @Override
    public void run() {
        logisticBase.startServiceVan(van);
        logisticBase.finishServiceVan(van);
    }
}
