package com.liukhtenko.multithreading.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CustomTimer extends TimerTask {
    static Logger logger = LogManager.getLogger();

    @Override
    public void run() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Integer> future = es.submit(new Watcher());
        es.shutdownNow();
        try {
            System.out.println("Total used terminals: " + future.get());
        } catch (ExecutionException | InterruptedException e) {
            logger.log(Level.ERROR, "Unknown number of terminals used", e);
        }
    }
}
