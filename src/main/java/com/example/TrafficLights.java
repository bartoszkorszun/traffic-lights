package com.example;

enum MenuOptions {
    ADD_ROAD    { @Override public void printOption() { System.out.println("1. Add road"); } },
    DELETE_ROAD { @Override public void printOption() { System.out.println("2. Delete road"); } },
    OPEN_SYSTEM { @Override public void printOption() { System.out.println("3. Open system"); } },
    EXIT        { @Override public void printOption() { System.out.println("0. Exit"); } };

    public abstract void printOption();
}

public class TrafficLights {
    private int numberOfRoads;
    private int inputInterval;

    public TrafficLights(int numberOfRoads, int inputInterval) {
        this.numberOfRoads = numberOfRoads;
        this.inputInterval = inputInterval;
    }

    public void printMenu() {
        for (MenuOptions option : MenuOptions.values()) {
            option.printOption();
        }
    }
}
