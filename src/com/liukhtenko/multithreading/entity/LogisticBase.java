package com.liukhtenko.multithreading.entity;


import java.util.ArrayDeque;
import java.util.Queue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticBase {
    Queue<Terminal> freeTerminal; // FIXME: 16.01.2020 make private
    Queue<Terminal> busyTerminal;
    final static int NUMBER_OF_TERMINALS = 3;
    private static LogisticBase instance;
    private static Lock lock = new ReentrantLock(true);
    static Condition condition = lock.newCondition();  // FIXME: 16.01.2020 static
    private static AtomicBoolean isInstanceCreated = new AtomicBoolean(false);

    private LogisticBase() {
        freeTerminal = new ArrayDeque<>(NUMBER_OF_TERMINALS);
        busyTerminal = new ArrayDeque<>();
        for (int i = 1; i <= NUMBER_OF_TERMINALS; i++) {
            Terminal terminal = new Terminal(i);
            freeTerminal.offer(terminal);
        }
    }

    public static LogisticBase getInstance() {
        if (!isInstanceCreated.get()) {
            lock.lock();
            if (instance == null) {
                try {
                    instance = new LogisticBase();
                    isInstanceCreated.set(true);
                } finally {
                    lock.unlock();
                }
            }
        }
        return instance;
    }

    public void startServiceVan(Van van) {
        try {
            System.out.println(van + " in tunnel <--");
            lock.lock();
            while (freeTerminal.size() <= 0) {
                System.out.println(van + " awaiting...");
                condition.await();
            }
            Terminal terminal = freeTerminal.poll();
            busyTerminal.offer(terminal);
            System.out.println(van + " begin to service in " + terminal);
            loadUnloadVan(van);
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println(van + " finished servicing -->"); // FIXME: 16.01.2020 in log
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void finishServiceVan() {
        try {
            lock.lock();
            Terminal terminal = busyTerminal.poll();
            freeTerminal.offer(terminal);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    private void loadUnloadVan(Van van) {
        if (van.isLoaded()) {
            van.setLoaded(false);
        } else {
            van.setLoaded(true);
        }
    }
}
