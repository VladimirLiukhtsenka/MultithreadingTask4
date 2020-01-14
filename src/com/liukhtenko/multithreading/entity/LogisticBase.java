package com.liukhtenko.multithreading.entity;


import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticBase {
    private Queue<Terminal> freeTerminal;
    private Queue<Terminal> busyTerminal;
    private final static int NUMBER_OF_TERMINALS = 2;
    private static LogisticBase instance;
    private static Lock lock = new ReentrantLock(true);
    Condition condition = lock.newCondition();

    private LogisticBase() {
        freeTerminal = new ArrayDeque<>(NUMBER_OF_TERMINALS);
        busyTerminal = new ArrayDeque<>();
        for (int i = 0; i < NUMBER_OF_TERMINALS; i++) {
            Terminal terminal = new Terminal(i);
            freeTerminal.offer(terminal);
        }
    }

    public static LogisticBase getInstance() { // FIXME: 14.01.2020 rename
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                try {
                    instance = new LogisticBase();
                } finally {
                    lock.unlock();
                }
            }
        }
        return instance;
    }

    //    public void receptionVan(Van van) {  //rename
//        try {
//            System.out.println("Start "+van+" service:");
//            lock.lock();
//            freeTerminal.remove();
//            while (freeTerminal.size() <= 0) {
//                condition.await();
//                System.out.println(van+"WAIT!!!");
//            }
//            condition.signal();
//            System.out.println("Service "+van+" is finish");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }
//    }
    public void startServiceVan(Van van) {
        try {
            System.out.println(van + " in tunnel <--");
            lock.lock();
            Terminal terminal = null;
            while (freeTerminal.size() <= 0) {
                System.out.println(van + " awaiting...");
                condition.await();
            }
            terminal = freeTerminal.poll();
            busyTerminal.offer(terminal);
            System.out.println(van + " begin to service");
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println(van + " finished servicing -->");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void finishServiceVan1(Van van) {
        try {
            lock.lock();
            Terminal terminal = null;
            terminal = busyTerminal.poll();
            freeTerminal.offer(terminal);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void finishVan(Van van) {  //rename
        try {
            lock.lock();
            Terminal terminal = null;
            TimeUnit.MILLISECONDS.sleep(2);//загрузка

            // van is going away
            terminal = busyTerminal.poll();
            freeTerminal.offer(terminal);

            condition.signal();

            System.out.println(van + " Отдал Мониторы");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
