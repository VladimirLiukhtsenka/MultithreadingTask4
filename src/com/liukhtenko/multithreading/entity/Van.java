package com.liukhtenko.multithreading.entity;

import java.util.Objects;

public class Van {
    private String name; // FIXME: 14.01.2020
    private boolean isLoaded;
    private boolean isPerishableGoods;

    public Van(String name, boolean isLoaded, boolean isPerishableGoods) {
        this.name = name;
        this.isLoaded = isLoaded;
        this.isPerishableGoods = isPerishableGoods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    public boolean isPerishableGoods() {
        return isPerishableGoods;
    }

    public void setPerishableGoods(boolean perishableGoods) {
        isPerishableGoods = perishableGoods;
    }

    @Override
    public boolean equals(Object o) {// FIXME: 14.01.2020
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Van van = (Van) o;
        return isLoaded == van.isLoaded &&
                isPerishableGoods == van.isPerishableGoods &&
                Objects.equals(name, van.name);
    }

    @Override
    public int hashCode() {// FIXME: 14.01.2020
        return Objects.hash(name, isLoaded, isPerishableGoods);
    }

    @Override
    public String toString() { // FIXME: 14.01.2020
        return "Van{" +
                "name='" + name + '\'' +
                ", isLoaded=" + isLoaded +
                ", isPerishableGoods=" + isPerishableGoods +
                '}';
    }
}
