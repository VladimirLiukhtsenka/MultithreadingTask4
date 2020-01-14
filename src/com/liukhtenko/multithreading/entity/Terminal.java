package com.liukhtenko.multithreading.entity;

import java.util.Objects;

public class Terminal {
private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Terminal(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) { // FIXME: 14.01.2020 
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Terminal terminal = (Terminal) o;
        return id == terminal.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Terminal{" +
                "id=" + id +
                '}';
    }
}
