package com.liukhtenko.multithreading.entity;

public class Terminal {
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int id) {
        this.number = id;
    }

    Terminal(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Terminal terminal = (Terminal) o;
        return number == terminal.number;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + number;
        return result;
    }

    @Override
    public String toString() {
        return "Terminal{" +
                "number=" + number +
                '}';
    }
}
