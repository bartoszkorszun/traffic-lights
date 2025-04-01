package com.example;

public class TrafficLights {

    private static TrafficLights instance;

    private static int numberOfRoads = 0;
    private static int inputInterval = 0;

    private TrafficLights(int numberOfRoads, int inputInterval) {
        TrafficLights.numberOfRoads = numberOfRoads;
        TrafficLights.inputInterval = inputInterval;
    }

    public static TrafficLights createTrafficLights(int numberOfRoads, int inputInterval) {
        if (instance == null) {
            instance = new TrafficLights(numberOfRoads, inputInterval);
        } 
        return instance;
    }

    public static TrafficLights getInstance() {
        return instance;
    }

    public static int getNumberOfRoads() {
        return numberOfRoads;
    }

    public static int getInputInterval() {
        return inputInterval;
    }
}
