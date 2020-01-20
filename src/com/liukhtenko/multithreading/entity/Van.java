package com.liukhtenko.multithreading.entity;

public class Van extends Thread {
    private String vanName;
    private boolean isLoaded;
    private boolean isPerishableGoods;
    private LogisticBase logisticBase = LogisticBase.getInstance();

    public Van() {
    }

    public Van(String name, boolean isLoaded, boolean isPerishableGoods) {
        this.vanName = name;
        this.isLoaded = isLoaded;
        this.isPerishableGoods = isPerishableGoods;
    }

    @Override
    public void run() {
        checkPriority(this);
        logisticBase.startServiceVan(this);
        logisticBase.finishServiceVan();
    }

    public String getVanNameName() {
        return vanName;
    }

    public void setVanName(String name) {
        this.vanName = name;
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

    private void checkPriority(Van van) {
        if (van.isPerishableGoods()) {
            van.setPriority(Thread.MAX_PRIORITY);
        } else {
            van.setPriority(Thread.MIN_PRIORITY);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Van van = (Van) o;
        if (van.isLoaded != isLoaded) {
            return false;
        }
        if (van.isPerishableGoods != isPerishableGoods) {
            return false;
        }
        return vanName != null ? vanName.equals(van.vanName) : van.vanName == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + vanName.hashCode();
        result = prime * result + Boolean.hashCode(isLoaded);
        result = prime * result + Boolean.hashCode(isPerishableGoods);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Van{vanName='").append(vanName).append('\'');
        sb.append(", isLoaded=").append(isLoaded);
        sb.append(", isPerishableGoods=").append(isPerishableGoods).append('}');
        return sb.toString();
    }
}
