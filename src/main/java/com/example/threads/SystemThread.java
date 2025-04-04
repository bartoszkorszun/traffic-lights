package com.example.threads;

import com.example.RoadQueue;
import com.example.TrafficLights;
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
    @SuppressWarnings("BusyWait")
    public void run() {
        int secoundsPassed = 0;

        while (true) {
            try {
                if (isOnDisplay) printSystemInfo(secoundsPassed);
                secoundsPassed++;
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
            System.out.println("! Interval: " + TrafficLights.getInputInterval() + " !");

            String[] roadNames = RoadQueue.getInstance().getRoadNames();
            if (roadNames != null) {
                System.out.println();
                for (String roadName : roadNames) {
                    if (roadName != null) {
                        System.out.println(roadName);
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
