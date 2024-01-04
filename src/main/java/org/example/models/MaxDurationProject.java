package org.example.models;

public class MaxDurationProject {
    private String name;
    private int monthCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(int monthCount) {
        this.monthCount = monthCount;
    }
    @Override
    public String toString() {
        return "MaxDurationProject{" +
                "name='" + name + '\'' +
                ", monthCount=" + monthCount +
                '}';
    }
}
