package com.example;

public class SystemThread extends Thread {
    
    @Override
    public void run() {
        int secoundsPassed = 0;
        while (true) {
            try {
                if (SystemState.getState() == SystemStateEnum.SYSTEM) { printSystemInfo(secoundsPassed); }
                secoundsPassed++;
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                System.out.println("System thread interrupted: " + e.getMessage());
                break; 
            }
        }
    }

    private void printSystemInfo(int secoundsPassed) {
        ClearConsole.clearConsole(); 
        System.out.println("! " + secoundsPassed + "s. have passed since the system startup !");
        System.out.println("! Number of roads: " + TrafficLights.getNumberOfRoads() + " !");
        System.out.println("! Interval: " + TrafficLights.getInputInterval() + " !");
        System.out.println("! Press \"Enter\" to open menu !");
    }
}
