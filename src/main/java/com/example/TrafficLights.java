package com.example;

public class TrafficLights {

    private static TrafficLights instance;

    private static int numberOfRoads = 0;
    private static int interval = 0;

    private TrafficLights(int numberOfRoads, int interval) {
        TrafficLights.numberOfRoads = numberOfRoads;
        TrafficLights.interval = interval;
    }

    public static void createTrafficLights(int numberOfRoads, int interval) {
        if (instance == null) {
            instance = new TrafficLights(numberOfRoads, interval);
        } 
    }

    public static TrafficLights getInstance() {
        return instance;
    }

    public static int getNumberOfRoads() {
        return numberOfRoads;
    }

    public static int getInterval() {
        return interval;
    }
}
