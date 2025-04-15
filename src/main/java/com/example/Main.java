package com.example;

import com.example.enums.SystemStateEnum;
import com.example.threads.MenuThread;
import com.example.threads.StateChangeThread;
import com.example.threads.SystemThread;
import com.example.utils.SharedScanner;
import com.example.utils.SystemState;

public class Main {
    public static void main(String[] args) {
        int numberOfRoads = -1;
        int inputInterval = -1;

        System.out.println("Welcome to the traffic management system!");

        while (true) {
            try {
                System.out.print("Input the number of roads: > ");
                numberOfRoads = Integer.parseInt(SharedScanner.getScannerInput());
                System.out.print("Input the interval of time: > ");
                inputInterval = Integer.parseInt(SharedScanner.getScannerInput());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    
        TrafficLights.createTrafficLights(numberOfRoads, inputInterval);
        RoadQueue.createRoadQueue(numberOfRoads);
        
        StateChangeThread stateChangeThread = StateChangeThread.getInstance();
        SystemThread systemThread = SystemThread.getInstance();
        MenuThread menuThread = MenuThread.getInstance();
        
        stateChangeThread.start();
        systemThread.start();
        menuThread.start();

        SystemState.setState(SystemStateEnum.MENU);
        SystemThread.setOnDisplay(true);
    }
}