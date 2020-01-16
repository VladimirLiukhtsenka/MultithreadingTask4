package com.liukhtenko.multithreading.entity;

import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CustomTimer extends TimerTask {
    @Override
    public void run() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Integer> future = es.submit(new Watcher());
        es.shutdownNow();
        try {
            System.out.println("WATCHER: "+future.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
