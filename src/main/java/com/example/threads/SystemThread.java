package com.example.threads;

import com.example.entities.Road;
import com.example.entities.RoadQueue;
import com.example.entities.TrafficLights;
import com.example.enums.SystemStateEnum;
import com.example.utils.ClearConsole;
import com.example.utils.SystemState;

public class SystemThread extends Thread {
    private static boolean isOnDisplay = false; 
    private static SystemThread systemThreadInstance = null;

    private SystemThread() {}

    public static SystemThread getInstance() {
        if (systemThreadInstance == null) {
            systemThreadInstance = new SystemThread();
        }
        return systemThreadInstance;
    }

    @Override
    public void run() {
        int secoundsPassed = 0;

        while (true) {
            try {
                if (isOnDisplay) printSystemInfo(secoundsPassed);
                secoundsPassed++;
                Road[] roadQueue = RoadQueue.getInstance().getRoadQueue();
                if (roadQueue != null) {
                    for (Road road : roadQueue) {
                        if (road != null) {
                            road.decreaseTimeLeft();
                        }
                    }
                }
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                System.out.println("System thread interrupted: " + e.getMessage());
                break; 
            }
        }
    }

    private void printSystemInfo(int secoundsPassed) {
        if (SystemState.getState() == SystemStateEnum.SYSTEM) {
            ClearConsole.clearConsole(); 
            System.out.println("! " + secoundsPassed + "s. have passed since the system startup !");
            System.out.println("! Number of roads: " + TrafficLights.getNumberOfRoads() + " !");
            System.out.println("! Interval: " + TrafficLights.getInterval() + " !");

            Road[] roadQueue = RoadQueue.getInstance().getRoadQueue();
            if (roadQueue != null) {
                System.out.println();
                for (Road road : roadQueue) {
                    if (road != null) {
                        System.out.println(road.toString());
                    }
                }
                System.out.println();
            }
            System.out.println("! Press \"Enter\" to open menu !");
        }
    }

    public static void setOnDisplay(boolean onDisplay) {
        isOnDisplay = onDisplay;
    }
}
