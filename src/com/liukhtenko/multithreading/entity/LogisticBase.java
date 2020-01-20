package com.liukhtenko.multithreading.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticBase {
    static Logger logger = LogManager.getLogger();
    Queue<Terminal> freeTerminal;
    Queue<Terminal> busyTerminal;
    private final static int NUMBER_OF_TERMINALS = 3;
    private static LogisticBase instance;
    private static Lock lock = new ReentrantLock(true);
    private Condition condition = lock.newCondition();
    private static AtomicBoolean isInstanceCreated = new AtomicBoolean(false);

    private LogisticBase() {
        freeTerminal = new ArrayDeque<>(NUMBER_OF_TERMINALS);
        busyTerminal = new ArrayDeque<>();
        for (int i = 1; i <= NUMBER_OF_TERMINALS; i++) {
            Terminal terminal = new Terminal(i);
            freeTerminal.offer(terminal);
        }
    }

    static LogisticBase getInstance() {
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

    void startServiceVan(Van van) {
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
            System.out.println(van + " finished servicing -->");
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Impossible to service " + van, e);
        } finally {
            lock.unlock();
        }
    }

    void finishServiceVan() {
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
