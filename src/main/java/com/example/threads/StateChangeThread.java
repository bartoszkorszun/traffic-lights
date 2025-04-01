package com.example.threads;

import com.example.SharedScanner;
import com.example.SystemState;
import com.example.enums.SystemStateEnum;

public class StateChangeThread extends Thread {

    @Override
    public void run() {
        while (true) { 
            String input = SharedScanner.getScannerInput();
            if (SystemState.getState() == SystemStateEnum.SYSTEM && input.isEmpty()) {
                SystemState.setState(SystemStateEnum.MENU);
                System.out.println("System state changed to MENU.");
            }
        }
    }
} 
