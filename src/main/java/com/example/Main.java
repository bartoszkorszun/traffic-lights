package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numberOfRoads = -1;
        int inputInterval = -1;

        System.out.println("Welcome to the traffic management system!");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                try {
                    System.out.print("Input the number of roads: > ");
                    numberOfRoads = scanner.nextInt();
                    System.out.print("Input the interval of time: > ");
                    inputInterval = scanner.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                    scanner.next();
                }
            }

        }
        
        TrafficLights.creTrafficLights(numberOfRoads, inputInterval);

        SystemThread systemThread = new SystemThread();
        systemThread.start();

        // SystemState.setState(SystemStateEnum.SYSTEM);
    }
}